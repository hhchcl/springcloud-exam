package com.deepexi.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author: hc
 */
public class MySelfRandomRule extends AbstractLoadBalancerRule {

    /*
        total = 0 当total=5时，才能往下走
        index = 0 当前对外提供服务的地址
        total需要重置为0，如果已经达到过一个5次，index就=1
     */
    private int total = 0; // 总共被调用的次数，目前要求每次机器被调用5次
    private int currentIndex = 0; // 当前提供服务的机器号

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    protected int chooseRandomInt(int serverCount) {
        return ThreadLocalRandom.current().nextInt(serverCount);
    }

    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        }
        Server server = null;
        while (server == null) {
            if (Thread.interrupted()) {
                return null;
            }

            List<Server> upList = lb.getReachableServers();
            List<Server> allList = lb.getAllServers();

            int serverCount = allList.size();

            if (serverCount == 0) {
                return null;
            }

            //int index = chooseRandomInt(serverCount);
            //server = upList.get(index);

            if (total < 5) {
                server = upList.get(currentIndex);
                total ++;
            } else {
                total = 0;
                currentIndex ++;
                if (currentIndex > upList.size()) {
                    currentIndex = 0;
                }
            }

            if (server == null) {
                Thread.yield();
                continue;
            }

            if (server.isAlive()) {
                return (server);
            }

            server = null;
            Thread.yield();
        }

        return server;
    }
}
