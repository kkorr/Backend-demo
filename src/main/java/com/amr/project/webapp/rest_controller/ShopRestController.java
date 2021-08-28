package com.amr.project.webapp.rest_controller;


import com.amr.project.converter.ShopMapper;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Image;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.CountryService;
import com.amr.project.service.abstracts.ImageService;
import com.amr.project.service.abstracts.ShopService;
import com.amr.project.service.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/shop")
public class ShopRestController {
    private ShopService shopService;
    private final ShopMapper shopMapper;
    private final CountryService countryService;
    private final UserService userService;
    private final ImageService imageService;

    @Autowired
    public ShopRestController(ShopService shopService, ShopMapper shopMapper, CountryService countryService, UserService userService,
                              ImageService imageService) {
        this.shopService = shopService;
        this.shopMapper = shopMapper;
        this.countryService = countryService;
        this.userService = userService;
        this.imageService = imageService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShopDto> getShop(@PathVariable("id") Long id) {
        return new ResponseEntity<>(ShopMapper.INSTANCE.shopToShopDto(shopService.getByKey(id)), HttpStatus.OK);
    }

    @PutMapping("/save")
    public ResponseEntity<Shop> saveItem(@RequestBody ShopDto shopDto) {

        Shop shop = shopMapper.shopDtoToShop(shopDto);

        Image image = Image.builder()
                .url(shop.getLogo().getUrl())
                .picture(shop.getLogo().getPicture())
                .isMain(true)
                .build();


        Shop oldShop = shopService.getByKey(shop.getId());
        shop.setLocation(countryService.getByName(shopDto.getLocation()));
        shop.setUser(userService.findByUsername(shopDto.getUsername()).get());
        shop.setItems(oldShop.getItems());
        shop.setDiscounts(oldShop.getDiscounts());
        shop.setReviews(oldShop.getReviews());
        shop.setRating(oldShop.getRating());
        shop.setPretendentToBeDeleted(oldShop.isPretendentToBeDeleted());
        shop.setLogo(image);
        shopService.update(shop);

        return new ResponseEntity<>(shop, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<Shop> addItem(@RequestBody ShopDto shopDto) {
        Image image = Image.builder()
                .url(shopDto.getLogo())
                .picture(shopDto.getLogoarray().getBytes())
                .isMain(true)
                .build();


        Shop shop = shopMapper.shopDtoToShop(shopDto);
        shop.setLocation(countryService.getByName(shopDto.getLocation()));
        shop.setUser(userService.findByUsername(shopDto.getUsername()).get());
        shop.setLogo(image);
        shopService.persist(shop);

        return new ResponseEntity<>(shop, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        shopService.deleteByKeyCascadeEnable(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
