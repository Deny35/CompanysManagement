//package pl.denys.karol.CompanysManagement;
//
//import org.junit.jupiter.api.Test;
//import pl.denys.karol.CompanysManagement.dto.CompanyDTO;
//import pl.denys.karol.CompanysManagement.model.Company;
//import pl.denys.karol.CompanysManagement.mapper.CompanyMapper;
//import org.mapstruct.factory.Mappers;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class CompanyMapperTest {
//
//    private final CompanyMapper companyMapper = Mappers.getMapper(CompanyMapper.class);
//
//    @Test
//    public void testToDTO() {
//        // Przykładowa encja
//        Company company = new Company();
//        company.setId(1L);
//        company.setName("Test Company");
//        company.setAddress("123 Street");
//        company.setCity("Test City");
//        company.setZipCode("12345");
//        company.setLatitude(40.7128);
//        company.setLongitude(-74.0060);
//
//        // Mapowanie encji do DTO
//        CompanyDTO companyDTO = companyMapper.toDTO(company);
//
//        // Sprawdzenie, czy mapowanie działa poprawnie
//        assertNotNull(companyDTO);
//        assertEquals(company.getId(), companyDTO.getId());
//        assertEquals(company.getName(), companyDTO.getName());
//        assertEquals(company.getAddress(), companyDTO.getAddress());
//        assertEquals(company.getCity(), companyDTO.getCity());
//        assertEquals(company.getZipCode(), companyDTO.getZipCode());
//        assertEquals(company.getLatitude(), companyDTO.getLatitude());
//        assertEquals(company.getLongitude(), companyDTO.getLongitude());
//    }
//
//    @Test
//    public void testToEntity() {
//        // Przykładowe DTO
//        CompanyDTO companyDTO = new CompanyDTO();
//        companyDTO.setId(1L);
//        companyDTO.setName("Test Company");
//        companyDTO.setAddress("123 Street");
//        companyDTO.setCity("Test City");
//        companyDTO.setZipCode("12345");
//        companyDTO.setLatitude(40.7128);
//        companyDTO.setLongitude(-74.0060);
//
//        // Mapowanie DTO do encji
//        Company company = companyMapper.toEntity(companyDTO);
//
//        // Sprawdzenie, czy mapowanie działa poprawnie
//        assertNotNull(company);
//        assertEquals(companyDTO.getId(), company.getId());
//        assertEquals(companyDTO.getName(), company.getName());
//        assertEquals(companyDTO.getAddress(), company.getAddress());
//        assertEquals(companyDTO.getCity(), company.getCity());
//        assertEquals(companyDTO.getZipCode(), company.getZipCode());
//        assertEquals(companyDTO.getLatitude(), company.getLatitude());
//        assertEquals(companyDTO.getLongitude(), company.getLongitude());
//    }
//}
//
