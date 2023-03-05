package com.lupo.suggestapi.spring;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.code.ssm.CacheFactory;
import com.google.code.ssm.config.AbstractSSMConfiguration;
import com.google.code.ssm.config.DefaultAddressProvider;
import com.google.code.ssm.providers.xmemcached.MemcacheClientFactoryImpl;
import com.google.code.ssm.providers.xmemcached.XMemcachedConfiguration;

import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.auth.AuthInfo;
import net.rubyeye.xmemcached.utils.AddrUtil;

@Configuration
public class MemCachierConfig extends AbstractSSMConfiguration {

    @Value("${MEMCACHED_SERVERS}")
    private String memcachedServers;

    @Value("${MEMCACHED_USERNAME}")
    private String memcachedUsername;

    @Value("${MEMCACHED_PASSWORD}")
    private String memcachedPassword;

    @Bean
    @Override
    public CacheFactory defaultMemcachedClient() {
        final String serverString = memcachedServers.replace(",",
                                                             " ");
        final List<InetSocketAddress> servers = AddrUtil.getAddresses(serverString);

        //TODO: Commented out sections are for security. Once this is all setup in AWS the password logic needs to be implemented.

        //        final AuthInfo authInfo = AuthInfo.plain(memcachedUsername,
        //                                                 memcachedPassword);

        final MemcachedClientBuilder builder = new XMemcachedClientBuilder(servers);

        //        for (InetSocketAddress server : servers) {
        //            builder.addAuthInfo(server,
        //                                authInfo);
        //        }

        //        final AuthInfo authInfo = AuthInfo.plain(memcachedUsername,
        //                                           memcachedPassword);
        //
        //        final Map<InetSocketAddress, AuthInfo> authInfoMap =
        //                new HashMap<>();
        //        for (InetSocketAddress server : servers) {
        //            authInfoMap.put(server,
        //                            authInfo);
        //        }

        final XMemcachedConfiguration memcachedConfig = new XMemcachedConfiguration();
        memcachedConfig.setUseBinaryProtocol(true);
        //        memcachedConfig.setAuthInfoMap(authInfoMap);

        final CacheFactory cacheFactory = new CacheFactory();
        cacheFactory.setCacheClientFactory(new MemcacheClientFactoryImpl());
        cacheFactory.setAddressProvider(new DefaultAddressProvider(serverString));
        cacheFactory.setConfiguration(memcachedConfig);
        return cacheFactory;
    }
}
