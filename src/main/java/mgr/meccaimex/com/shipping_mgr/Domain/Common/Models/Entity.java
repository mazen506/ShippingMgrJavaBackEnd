package mgr.meccaimex.com.shipping_mgr.Domain.Common.Models;

import jakarta.persistence.*;
import lombok.*;
import mgr.meccaimex.com.shipping_mgr.Domain.Common.Interfaces.IDomainEvent;
import mgr.meccaimex.com.shipping_mgr.Domain.Common.Interfaces.IEntity;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class Entity<TId> implements IEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    public TId Id;

//    @ElementCollection
//    private final List<IDomainEvent> _domainEvents = Collections.unmodifiableList(new ArrayList<>());

//    public void ClearDomainEvents()
//    {
//        _domainEvents.clear();
//    }

}
