package com.ontheroad.entity;

import com.ontheroad.api.request.Request;
import com.ontheroad.api.validate.BaseGroup;

import javax.validation.constraints.NotNull;

/**
 * Created by Administrator on 2017/6/8 0008.
 */
public class IndexRequest extends Request {
    private static final long serialVersionUID = -200458917316658521L;

    public interface IndexAdd extends BaseGroup {

    }

    @NotNull(groups = {IndexAdd.class})
    private String title;

    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
