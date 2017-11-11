package yonky.mynews.model.http.response;

/**
 * Created by Administrator on 2017/11/7.
 */

public class GoldHttpResponse<T>{
    private T results;

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
