package org.rhr.elasticsearch;

import org.elasticsearch.client.Client;
import org.elasticsearch.node.Node;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

public final class EsClientProvider {

    private final Client client;

    private static final EsClientProvider INSTANCE = new EsClientProvider();

    private EsClientProvider() {
        Node node = nodeBuilder().clusterName("nirvana").client(true).node();
        this.client = node.client();
    }

    public static EsClientProvider instance() {
        return  INSTANCE;
    }

    public Client get() {
        return client;
    }

}
