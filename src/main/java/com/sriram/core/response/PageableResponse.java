package com.sriram.core.response;

import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class PageableResponse extends BaseRestResponse {

    private static final long serialVersionUID = -2846910247972036035L;

    private long totalElements;

    private int totalPages;

    private long size;
    private long numberOfElements;
    private int number;
    private boolean last;
    private boolean first;

    private boolean sorted;
    private boolean empty;

    public PageableResponse(Boolean ok, String code, Page page) {
        this.setOk(ok);
        this.setCode(code);
        if (page != null) {
            this.setResult(page.getContent());
            this.totalElements = page.getTotalElements();
            this.totalPages = page.getTotalPages();
            this.size = page.getSize();
            this.numberOfElements = page.getNumberOfElements();
            this.number = page.getNumber();
            this.last = page.isLast();
            this.first = page.isFirst();
            this.sorted = page.getSort().isSorted();
            this.empty = page.isEmpty();
        }
    }

}
