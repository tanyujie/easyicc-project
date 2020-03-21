package org.easymis.easyicc.web.chat.n3r.idworker;
public interface RandomCodeStrategy {

    void init();

    int prefix();

    int next();

    void release();
}
