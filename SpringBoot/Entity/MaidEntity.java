package sut.se.g18.Entity;
import javax.persistence.*;
import lombok.*;

@Entity
@Data
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "MAID")
public class MaidEntity {
    @Id
    @SequenceGenerator(name="maid_seq",sequenceName="maid_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="maid_seq")
    @Column(name="maidId",unique = true, nullable = false)
    private @NonNull Long maidId;
    private @NonNull String maidName;
    private @NonNull String tel;

    //Many To One with Company
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = CompanyEntity.class)
    private CompanyEntity companyForMaid;

    //Many To One with Customer
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = CustomerEntity.class)
    private CustomerEntity customer;

    //Many To One with MaidStatus
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = MaidStatusEntity.class)
    private MaidStatusEntity status;

    public CompanyEntity getCompanyForMaid() {
        return companyForMaid;
    }

    public void setCompanyForMaid(CompanyEntity companyForMaid) {
        this.companyForMaid = companyForMaid;
    }

    public Long getMaidId() {
        return maidId;
    }

    public void setMaidId(Long maidId) {
        this.maidId = maidId;
    }

    public String getTel() {
        return tel;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMaidName() {
        return maidName;
    }

    public void setMaidName(String maidName) {
        this.maidName = maidName;
    }

    public MaidStatusEntity getStatus() {
        return status;
    }

    public void setStatus(MaidStatusEntity status) {
        this.status = status;
    }
}
