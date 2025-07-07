package mgr.meccaimex.com.shipping_mgr.Domain.Common.Models;

import org.springframework.context.ApplicationContextException;

import java.time.LocalDateTime;

public class ExecInfo  {
    private LocalDateTime at;
    private String by;

    public static ExecInfo Create(LocalDateTime at, String by) {
        if (at == null || by == null) {
            throw new ApplicationContextException("Invalid ExecInfo");
        }
        var obj = new ExecInfo();
        obj.at = at;
        obj.by = by;

        return obj;

    }

};
