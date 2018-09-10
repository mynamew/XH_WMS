package com.jzk.xh_wms.http.parser;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Url解析器
 * <p>
 * Created by jess on 17/07/2017 17:44
 * Contact with jess.yan.effort@gmail.com
 */

public interface UrlParser {
    /**
     * 用来替换 @{@link Request#url} 达到动态切换 Url
     *
     * @param domainUrl
     * @return
     */
    HttpUrl parseUrl(HttpUrl domainUrl, HttpUrl url);
}
