package org.rhr;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.client.Client;
import org.rhr.elasticsearch.EsClientProvider;
import org.rhr.elasticsearch.dao.BankAccountDao;
import org.rhr.elasticsearch.model.BankAccount;
import org.rhr.utils.JsonSerializer;
import org.rhr.utils.NamesProvider;

import java.math.BigDecimal;
import java.util.List;

public class App {



    public static void main(String[] args) {
        final ObjectMapper objectMapper = new ObjectMapper();
        final JsonSerializer jsonSerializer = new JsonSerializer(objectMapper);

        Client client = EsClientProvider.instance().get();

        BankAccountDao bankAccountDao = new BankAccountDao(client, jsonSerializer);

        insertAccounts(bankAccountDao);

//        List<BankAccount> accounts = bankAccountDao.getByOwner("Eric Cartman");

//        BankAccount acccount = bankAccountDao.getById("31315198759671");

//        System.out.println(accounts.size());
//        System.out.println(accounts);

        System.out.println("Done");


    }

    private static void insertAccounts(BankAccountDao bankAccountDao) {
        for (int i = 0; i < 1000000 ; ++i) {
            bankAccountDao.save(new BankAccount(String.valueOf(System.nanoTime()), BigDecimal.TEN,
                    NamesProvider.getRandomName()));
        }
    }


}
