package mgr.meccaimex.com.shipping_mgr.Domain.Common.Interfaces;

import java.util.List;

public interface IEntity {
    List<IDomainEvent> GetDomainEvents();
    void ClearDomainEvents();
}
