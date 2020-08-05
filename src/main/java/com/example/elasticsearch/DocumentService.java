package com.example.elasticsearch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;

import java.io.IOException;
import java.util.List;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/5 10:30
 */
public class DocumentService {

    private final RestHighLevelClient client = ClientService.getClient();

    /**
     * index a doc in the form of json
     *
     * @param index index name
     * @param json doc as a json string
     * @param forceRefresh force refresh the index?
     * @return doc id after index
     */
    public String index(String index, String json, boolean forceRefresh) {
        IndexRequest request = new IndexRequest(index);
        request.source(json, XContentType.JSON);
        try {
            var response = client.index(request, RequestOptions.DEFAULT);
            if (forceRefresh) {
                response.forcedRefresh();
            }
            return response.getId();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String index(String index, String json) {
        return index(index, json, false);
    }

    /**
     * index a doc in the form of object
     *
     * @param index index name
     * @param t doc as a object
     * @param <T> Object type
     * @param forceRefresh force refresh the index?
     * @return doc id after index
     */
    public <T> String index(String index, T t, boolean forceRefresh) {
        ObjectMapper mapper = new ObjectMapper();
        String json;
        try {
            json = mapper.writeValueAsString(t);
            return index(index, json, forceRefresh);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> String index(String index, T t) {
        return index(index, t, false);
    }

    /**
     * del doc by id
     *
     * @param index index name
     * @param id doc id
     */
    public void delete(String index ,String id) {
        DeleteRequest request = new DeleteRequest(index, id);
        try {
            client.delete(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * delete doc by query
     *
     * @param queryBuilder query builder
     * @param indices index names
     */
    public void deleteByQuery(QueryBuilder queryBuilder, String... indices) {
        DeleteByQueryRequest request = new DeleteByQueryRequest(indices);
        request.setQuery(queryBuilder).setRefresh(true);
        try {
            client.deleteByQuery(request,RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * bulk index docs
     *
     * @param index index name
     * @param docs docs
     * @param <T> doc type
     * @return index successful?
     */
    public <T> boolean bulkIndex(String index, List<T> docs) {
        BulkRequest request = new BulkRequest();
        ObjectMapper mapper = new ObjectMapper();
        try {
            docs.forEach(d -> {
                try {
                    request.add(new IndexRequest(index).source(mapper.writeValueAsString(d), XContentType.JSON));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            });
            var response = client.bulk(request, RequestOptions.DEFAULT);
            return response.hasFailures();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
