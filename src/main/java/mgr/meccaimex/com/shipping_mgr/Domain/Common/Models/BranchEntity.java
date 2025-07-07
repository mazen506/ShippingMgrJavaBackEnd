package mgr.meccaimex.com.shipping_mgr.Domain.Common.Models;

import mgr.meccaimex.com.shipping_mgr.Domain.Common.Interfaces.IBranchEntity;
import mgr.meccaimex.com.shipping_mgr.Domain.Common.Interfaces.IEntity;

public abstract class BranchEntity implements IBranchEntity {
    private Integer branchId;
    @Override
    public Integer getBranchId()
    {
        return branchId;
    }
}
