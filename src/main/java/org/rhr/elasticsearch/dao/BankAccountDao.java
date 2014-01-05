package org.rhr.elasticsearch.dao;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.rhr.elasticsearch.model.BankAccount;
import org.rhr.utils.JsonSerializer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BankAccountDao {

    private final static String INDEX_NAME = "bank-account";
    private final static String DATA_TYPE = "bank-account";


    private final Client client;

    private final JsonSerializer jsonSerializer;


    public BankAccountDao(Client client, JsonSerializer jsonSerializer) {
        this.client = client;
        this.jsonSerializer = jsonSerializer;
    }


    public void save(BankAccount bankAccount) {
        final String serialized = jsonSerializer.serialize(bankAccount);
        IndexResponse response = client.prepareIndex(INDEX_NAME, DATA_TYPE, bankAccount.getAccountNumber())
                .setSource(serialized).execute().actionGet();
    }

    public BankAccount getById(final String accountNumber) {
        GetResponse response = client.prepareGet(INDEX_NAME, DATA_TYPE, accountNumber).execute().actionGet();
        if (response.isExists()) {
            return jsonSerializer.deserialize(response.getSourceAsString(), BankAccount.class);
        } else {
            return null;
        }
    }

    public List<BankAccount> getByOwner(final String owner) {
        SearchResponse response = client.prepareSearch(INDEX_NAME).setTypes(DATA_TYPE)
                .setQuery(QueryBuilders.matchQuery("owner", owner))
                .setSize(1000)
                .execute()
                .actionGet();
        Iterator<SearchHit> searchHitsIterator = response.getHits().iterator();
        List<BankAccount> accounts = new ArrayList<BankAccount>();
        while (searchHitsIterator.hasNext()) {
            BankAccount account =
                    jsonSerializer.deserialize(searchHitsIterator.next().getSourceAsString(), BankAccount.class);
            accounts.add(account);
        }
        return accounts;
    }

}
