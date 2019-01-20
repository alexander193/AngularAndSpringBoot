package sut.se.g18.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sut.se.g18.Entity.*;
import sut.se.g18.Repository.*;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ContractController {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private MaidRepository maidRepository;
    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AdminAccountRepository adminAccountRepository;
    @Autowired
    private ContractTypeRepository contractTypeRepository;
    @Autowired
    private PaymentStatusRepository paymentStatusRepository;
    @Autowired
    private MaidStatusRepository maidStatusRepository;

    public ContractController(CompanyRepository companyRepository, ContractRepository contractRepository,
                              MaidRepository maidRepository, PromotionRepository promotionRepository,
                              CustomerRepository customerRepository, AdminAccountRepository adminAccountRepository,
                              ContractTypeRepository contractTypeRepository, PaymentStatusRepository paymentStatusRepository,
                              MaidStatusRepository maidStatusRepository){
        this.companyRepository = companyRepository;
        this.contractRepository = contractRepository;
        this.maidRepository = maidRepository;
        this.promotionRepository = promotionRepository;
        this.customerRepository = customerRepository;
        this.adminAccountRepository = adminAccountRepository;
        this.contractTypeRepository = contractTypeRepository;
        this.paymentStatusRepository = paymentStatusRepository;
        this.maidStatusRepository = maidStatusRepository;
    }

    @GetMapping(path ="/admin/{adminUsername}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AdminAccountEntity getAdminAccount(@PathVariable String adminUsername){
        AdminAccountEntity admin = adminAccountRepository.findByadminUsername(adminUsername);
        return admin;
    }

    @GetMapping(path ="/company", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<CompanyEntity> getCompany(){
        return companyRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping(path ="/customer", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<CustomerEntity> customer(){
        return customerRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping(path ="/maid", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<MaidEntity> maidSelect(){
        return maidRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping(path = "/maid/getdata/{companySelect}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<MaidEntity> getMaidSelect(@PathVariable String companySelect) {
        CompanyEntity company = companyRepository.findBycompanyName(companySelect);
        Collection<MaidEntity> M = maidRepository.findBycompanyForMaid(company);
        return M.stream().collect(Collectors.toList());
    }

    @GetMapping(path ="/promotion", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<PromotionEntity> promotion(){
        return promotionRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping(path = "/promotion/getdata/{companySelect}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<PromotionEntity> getPromotion(@PathVariable String companySelect) {
        CompanyEntity company = companyRepository.findBycompanyName(companySelect);
        Collection<PromotionEntity> P = promotionRepository.findBycompany(company);
        return P.stream().collect(Collectors.toList());
    }

    @GetMapping(path ="/contractType", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<ContractTypeEntity> getContractType(){
        return contractTypeRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping(path ="/paymentStatus", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<PaymentStatusEntity> paymentStatus(){
        return paymentStatusRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/contract/{companySelect}/{maidSelect}/{contractTypeSelect}/{promotionSelect}/{dateStartInput}")
    public ContractEntity newContract(@RequestBody ContractEntity contractEntity, @PathVariable String companySelect,
                                     @PathVariable String maidSelect, @PathVariable String contractTypeSelect,
                                     @PathVariable String promotionSelect, @PathVariable Date dateStartInput) {
        ContractEntity newContract = new ContractEntity();

        CompanyEntity company = companyRepository.findBycompanyName(companySelect);
        newContract.setCompany(company);

        MaidEntity maid = maidRepository.findBymaidName(maidSelect);
        newContract.setMaid(maid);

        ContractTypeEntity type = contractTypeRepository.findBycontractType(contractTypeSelect);
        newContract.setContractType(type);

        CustomerEntity customer = maid.getCustomer();
        newContract.setCustomer(customer);

        PromotionEntity promotion = new PromotionEntity();
        if(!(promotionSelect.equals("No Promotion"))){
            promotion = promotionRepository.findBytitle(promotionSelect);
            newContract.setPromotion(promotion);
        }
        System.out.println(promotion);
        newContract.setDateStart(dateStartInput);

        PaymentStatusEntity status = paymentStatusRepository.findBypaymentStatus("ค้างชำระ");
        newContract.setStatus(status);

        return contractRepository.save(newContract);
    }

}
