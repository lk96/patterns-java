package org.kevin.prototype.ambassador;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.Thread.sleep;

/**
 * 以安全的方式添加日志记录，延迟测试和服务使用情况，当发生连接问题时，不会增加远程服务器压力
 */
@Slf4j
public class ServiceAmbassador implements IRemoteService {

    /**
     * 重试次数
     */
    private static final int RETRIES = 3;

    /**
     * 延迟
     */
    private static final int DELAY_MS = 3000;

    public ServiceAmbassador() {
    }

    @Override
    public long doRemoteFunction(int value) {
        return safeCall(value);
    }

    private long safeCall(int value) {
        int retries = 0;
        long result = RemoteServiceStatus.FAILURE.getRemoteServiceStatusValue();
        for (int i = 0; i < RETRIES; i++) {
            if (retries >= RETRIES) {
                return RemoteServiceStatus.FAILURE.getRemoteServiceStatusValue();
            }
            if ((result = checkLatency(value)) == RemoteServiceStatus.FAILURE.getRemoteServiceStatusValue()) {
                log.info("Failed to reach remote:(" + (i + 1) + ")");
                retries++;
                try {
                    sleep(DELAY_MS);
                } catch (InterruptedException e) {
                    log.info("Thread sleep state interrupted", e);
                    Thread.currentThread().interrupt();
                }
            } else {
                break;
            }
        }
        return result;
    }

    private long checkLatency(int value) {
        long start = System.currentTimeMillis();
        long result = RemoteService.getRemoteService().doRemoteFunction(value);
        long timeTaken = System.currentTimeMillis() - start;
        log.info("Time taken (ms):" + timeTaken);
        return result;
    }
}
