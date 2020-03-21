package org.easymis.easyicc.web.chat.n3r.idworker;

public interface WorkerIdStrategy {

    void initialize();

    long availableWorkerId();

    void release();
}
