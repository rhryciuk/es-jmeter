package org.rhr.elasticsearch.jmeter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.rhr.elasticsearch.EsClientProvider;
import org.rhr.elasticsearch.dao.BankAccountDao;
import org.rhr.elasticsearch.model.BankAccount;
import org.rhr.utils.JsonSerializer;
import org.rhr.utils.NamesProvider;

import java.util.List;

public class ElasticsearchSampler extends AbstractJavaSamplerClient {

    private final BankAccountDao bankAccountDao;


    public ElasticsearchSampler() {
        final ObjectMapper objectMapper = new ObjectMapper();
        final JsonSerializer jsonSerializer = new JsonSerializer(objectMapper);

        this.bankAccountDao = new BankAccountDao(EsClientProvider.instance().get(), jsonSerializer);
    }


    public ElasticsearchSampler(BankAccountDao bankAccountDao) {
        this.bankAccountDao = bankAccountDao;
    }

    @Override
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        SampleResult result = new SampleResult();
        result.sampleStart(); // start stopwatch

        List<BankAccount> accounts = bankAccountDao.getByOwner(NamesProvider.getRandomName());

        result.sampleEnd();
        result.setSuccessful(!accounts.isEmpty());
        result.setResponseMessage("Action performed successfully: " + !accounts.isEmpty());
        result.setResponseCodeOK();
        return result;
    }

}
