package sender;

import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationService;

public class LocalizationServiceMock implements LocalizationService {
    String text;

    @Override
    public String locale(Country country) {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
