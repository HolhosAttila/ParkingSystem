package managedbeans;

import lombok.Getter;
import lombok.Setter;
import model.Address;
import model.CarPark;
import model.utils.CarParkType;
import service.CarParkService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by holhosa on 2017.09.20..
 */
@ManagedBean(name="carParkMBean")
@ViewScoped
@Getter
@Setter
public class CarParkMBean extends AbstractMBean {

    private List<CarPark> carParks;
    private CarPark selectedCarPark;
    private CarPark editedCarPark;
    private List<CarParkType> carParkTypes;

    @EJB
    private CarParkService carParkService;

    @Inject
    private MapMBean mapMBean;

    @PostConstruct
    public void init(){
        carParks = carParkService.getAllCarPark();
        pageViewType = PageViewType.VIEW;
        carParkTypes = new ArrayList<CarParkType>(){{
            add(CarParkType.PARKING_HOUSE);
            add(CarParkType.OUTDOOR_CAR_PARK);
            add(CarParkType.UNDERGROUND_GARAGE);
        }};
    }

    @Override
    public void showNewEditPanel() {
        super.showNewEditPanel();
        editedCarPark = new CarPark();
        editedCarPark.setAddress(new Address());
        editedCarPark.getAddress().setCity("Debrecen");
        editedCarPark.getAddress().setCountry("Magyarország");
    }

    @Override
    public void showModifyEditPanel() {
        super.showModifyEditPanel();
        editedCarPark = selectedCarPark;
    }

    public void createCarPark() {
        carParkService.addCarPark(editedCarPark);
        carParks = carParkService.getAllCarPark();
        pageViewType = PageViewType.VIEW;
        mapMBean.addCarPark(editedCarPark);
        editedCarPark = null;
    }


    public void cancelEdit() {
        pageViewType = PageViewType.VIEW;
        editedCarPark = null;
    }

    public boolean newButtonDisabled() {
        if (PageViewType.VIEW.equals(pageViewType)) {
            return false;
        }
        return true;
    }

    public boolean deleteButtonDisabled() {
        if (PageViewType.VIEW.equals(pageViewType) && selectedCarPark != null) {
            return false;
        }
        return true;
    }
}
