package com.sy.chainproject.handler;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * @ data 2018/4/27 16:00
 *   弱引用 防止内存 泄露
 */

public class BaseHandler extends Handler {
        private BaseHandler() {
            throw new UnsupportedOperationException("u can't instantiate me...");
        }
        public static class HandlerHolder extends Handler {
            WeakReference<OnReceiveMessageListener> mListenerWeakReference;

            public HandlerHolder(OnReceiveMessageListener listener) {
                mListenerWeakReference = new WeakReference<>(listener);
            }

            @Override
            public void handleMessage(Message msg) {
                if (mListenerWeakReference != null && mListenerWeakReference.get() != null) {
                    mListenerWeakReference.get().handlerMessage(msg);
                }
            }
        }
        /**
         * 收到消息回调接口
         */
        public interface OnReceiveMessageListener {
            void handlerMessage(Message msg);
        }
}
