package edu.sru.cpsc.webshopping.repository.widgets;

import edu.sru.cpsc.webshopping.domain.widgets.WidgetImage;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface WidgetImageRepository<T extends WidgetImage> extends CrudRepository<T, Long>{
}
