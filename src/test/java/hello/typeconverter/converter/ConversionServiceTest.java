package hello.typeconverter.converter;

import hello.typeconverter.type.IpPort;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;

import static org.assertj.core.api.Assertions.*;

public class ConversionServiceTest {

    @Test
    void conversionService() {
        // DefaultConversionService 객체 생성
        DefaultConversionService conversionService = new DefaultConversionService();
        // conversionService 객채에 converter 등록
        conversionService.addConverter(new StringToIntegerConverter());
        conversionService.addConverter(new IntegerToStringConverter());
        conversionService.addConverter(new StringToIpPortConverter());
        conversionService.addConverter(new IpPortToStringConverter());
        // conversionService.convert source 에 String 10 을 넣고 Integer 로 반환하고싶다.
//        Integer result = conversionService.convert("10", Integer.class);
        // 등록된 StringTooIntegerConverter 가 사용되며 Integer 로 변환된 10이 result 값.
//        System.out.println("result = " + result);
        assertThat(conversionService.convert("10", Integer.class)).isEqualTo(10);
        // source 에 Integer10 , 변환되어질 값에 String -> conversionService 에 등록된
        // IntegerToStringConverter 를 자동으로 사용하여 문자열 "10" 이 나온다.
        assertThat(conversionService.convert(10, String.class)).isEqualTo("10");
        assertThat(conversionService.convert("127.0.0.1:8080", IpPort.class)).isEqualTo(new IpPort("127.0.0.1", 8080));
        assertThat(conversionService.convert(new IpPort("127.0.0.1", 8080), String.class)).isEqualTo("127.0.0.1:8080");
    }
}
// converter 를 등록할땐 상세 내용을 알아야 하지만 사용할땐 몰라도 된다.