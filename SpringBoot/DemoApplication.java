package sut.se.g18;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sut.se.g18.Entity.*;
import sut.se.g18.Repository.*;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Bean
	ApplicationRunner init(ContractRepository contractRepository, CompanyRepository companyRepository,
                           MaidRepository maidRepository, PromotionRepository promotionRepository,
                           CustomerRepository customerRepository, AdminAccountRepository adminAccountRepository,
                           ContractTypeRepository contractTypeRepository, PaymentStatusRepository paymentStatusRepository,
						   MaidStatusRepository maidStatusRepository){
		return args -> {
		    AdminAccountEntity admin = new AdminAccountEntity();
		    admin.setAdminUsername("1234");
		    admin.setAdminPassword("4321");
		    adminAccountRepository.save(admin);

		    PaymentStatusEntity s1 = new PaymentStatusEntity();
		    s1.setPaymentStatus("ค้างชำระ");
		    paymentStatusRepository.save(s1);
		    PaymentStatusEntity s2 = new PaymentStatusEntity();
		    s2.setPaymentStatus("จ่ายแล้ว");
		    paymentStatusRepository.save(s2);

		    MaidStatusEntity stat1 = new MaidStatusEntity();
		    stat1.setStatus("ว่าง");
		    maidStatusRepository.save(stat1);
		    MaidStatusEntity stat2 = new MaidStatusEntity();
		    stat2.setStatus("จอง");
		    maidStatusRepository.save(stat2);
		    MaidStatusEntity stat3 = new MaidStatusEntity();
		    stat3.setStatus("ทำสัญญาอยู่");
		    maidStatusRepository.save(stat3);

		    ContractTypeEntity type1 = new ContractTypeEntity();
		    type1.setContractType("1 Day");
		    contractTypeRepository.save(type1);
			ContractTypeEntity type2 = new ContractTypeEntity();
			type2.setContractType("1 Week");
			contractTypeRepository.save(type2);
			ContractTypeEntity type3 = new ContractTypeEntity();
			type3.setContractType("1 Month");
			contractTypeRepository.save(type3);
			ContractTypeEntity type4 = new ContractTypeEntity();
			type4.setContractType("6 Months");
			contractTypeRepository.save(type4);
			ContractTypeEntity type5 = new ContractTypeEntity();
			type5.setContractType("1 Year");
			contractTypeRepository.save(type5);

			CustomerEntity cus = new CustomerEntity();
			cus.setCustomerName("Pitchayut CheeseJa");
			customerRepository.save(cus);

		    CompanyEntity c1 = new CompanyEntity();
		    c1.setCompanyName("Valve");
		    companyRepository.save(c1);
			CompanyEntity c2 = new CompanyEntity();
			c2.setCompanyName("Steam");
			companyRepository.save(c2);

			MaidEntity m1 = new MaidEntity();
			CompanyEntity com = companyRepository.findBycompanyName("Valve");
			m1.setCompanyForMaid(com);
			CustomerEntity customer = customerRepository.findBycustomerName("Pitchayut CheeseJa");
			m1.setCustomer(customer);
			m1.setMaidName("สมศรี งานดี");
			m1.setTel("0935395533");
			MaidStatusEntity statusMaid = maidStatusRepository.findBystatus("จอง");
			m1.setStatus(statusMaid);
			maidRepository.save(m1);

		    PromotionEntity p1 = new PromotionEntity();
		    p1.setTitle("Discount");
		    CompanyEntity company = companyRepository.findBycompanyName("Valve");
		    p1.setCompany(company);
		    promotionRepository.save(p1);

            ContractEntity con = new ContractEntity();
            CompanyEntity company1 = companyRepository.findBycompanyName("Valve");
            con.setCompany(company1);


            PromotionEntity pro = promotionRepository.findBytitle("Discount");
            con.setPromotion(pro);

            CustomerEntity cus1 = customerRepository.findBycustomerName("Pitchayut CheeseJa");
            con.setCustomer(cus1);

            ContractTypeEntity type = contractTypeRepository.findBycontractType("1 Year");
            con.setContractType(type);

            PaymentStatusEntity p3 = paymentStatusRepository.findBypaymentStatus("ค้างชำระ");
            con.setStatus(p3);
            contractRepository.save(con);
		};
	}
}
