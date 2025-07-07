package mgr.meccaimex.com.shipping_mgr.Domain.Common.Models;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@MappedSuperclass
public class AggregateRoot<TId> extends AuditableEntity<TId> {
    public AggregateRoot(TId tId) {
        super(tId);
    }
}
