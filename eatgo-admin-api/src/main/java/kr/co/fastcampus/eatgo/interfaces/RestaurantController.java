package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.application.RestaurantService;
import kr.co.fastcampus.eatgo.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin
@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public List<Restaurant> list(){
        List<Restaurant> restaurants  = restaurantService.getRestaurants();

        return restaurants;
    }

    // ex) http://localhost:8080/restaurants/1004
    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id){
        //기본정보 + 메뉴정보

        Restaurant restaurant  = restaurantService.getRestaurant(id);
        

        return restaurant;
}

    //ResponseEntity 상태를 같이 넘겨줌
    @PostMapping("/restaurants")
    public ResponseEntity<?> create(@Valid @RequestBody Restaurant resource) throws URISyntaxException {

        //1. builder 사용
        Restaurant restaurant = restaurantService.addRestaurant(
                Restaurant.builder()
                        .categoryId(resource.getCategoryId())
                        .name(resource.getName())
                        .address(resource.getAddress())
                        .build());


//        Restaurant restaurant = new Restaurant(name, address);
//        restaurant = restaurantService.addRestaurant(restaurant);

//        Restaurant restaurant = restaurantService.addRestaurant(new Restaurant(name, address));

        URI location = new URI("/restaurants/" + restaurant.getId());
        return ResponseEntity.created(location).body("{}");
    }

    @PatchMapping("/restaurants/{id}")
    public String update(@PathVariable("id") Long id,
                         @Valid @RequestBody Restaurant resoure) {

        String name = resoure.getName();
        String address = resoure.getAddress();
        restaurantService.updateRestaurant(id, name, address);
        return "{}";
    }

}
