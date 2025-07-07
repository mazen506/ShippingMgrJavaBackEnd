package mgr.meccaimex.com.shipping_mgr.Domain.Common.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import mgr.meccaimex.com.shipping_mgr.Domain.Common.Interfaces.IAuditableEntity;
import mgr.meccaimex.com.shipping_mgr.Domain.Common.Interfaces.IDomainEvent;
import mgr.meccaimex.com.shipping_mgr.Domain.Common.Interfaces.IEntity;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class AuditableEntity<TId> extends Entity<TId>
                                    implements  IAuditableEntity {

    public AuditableEntity(TId tId) {
        super(tId);
    }

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "at", column = @Column(name = "created_at")),
            @AttributeOverride(name = "by", column = @Column(name = "created_by"))
    })
   ExecInfo created;

   @Embedded
   @AttributeOverrides({
            @AttributeOverride(name = "at", column = @Column(name = "updated_at")),
            @AttributeOverride(name = "by", column = @Column(name = "updated_by"))
    })
    ExecInfo updated;

    @Override
    public List<IDomainEvent> GetDomainEvents() {
        return List.of();
    }

    @Override
    public void ClearDomainEvents() {

    }

}
