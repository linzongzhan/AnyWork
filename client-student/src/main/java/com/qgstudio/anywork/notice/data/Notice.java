package com.qgstudio.anywork.notice.data;

import java.lang.reflect.Method;

public class Notice extends Message {
    public int messageId;
    public String title;
    public String content;
    public String publisher;
    public int status;// 0为未读，1为已读
}
