package cn.itcast.hotel.web;

import cn.itcast.hotel.pojo.Hotel;
import cn.itcast.hotel.pojo.PageResult;
import cn.itcast.hotel.service.IHotelService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;

/**
 * 酒店控制器
 *
 * @author liudo
 * @date 2023/08/23
 */
@RestController
@RequestMapping("/hotel")
@RequiredArgsConstructor
public class HotelController {

    private final IHotelService hotelService;

    /**
     * 按id查询
     *
     * @param id id
     * @return {@link Hotel}
     */
    @GetMapping("/{id}")
    public Hotel queryById(@PathVariable("id") Long id) {
        return hotelService.getById(id);
    }

    /**
     * 酒店列表
     *
     * @param page 页面
     * @param size 尺寸
     * @return {@link PageResult}
     */
    @GetMapping("/list")
    public PageResult hotelList(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Page<Hotel> result = hotelService.page(new Page<>(page, size));

        return new PageResult(result.getTotal(), result.getRecords());
    }

    /**
     * 新增酒店
     *
     * @param hotel 酒店
     */
    @PostMapping
    public void saveHotel(@RequestBody Hotel hotel) {
        hotelService.save(hotel);
    }

    /**
     * 按id更新
     *
     * @param hotel 酒店
     */
    @PutMapping()
    public void updateById(@RequestBody Hotel hotel) {
        if (hotel.getId() == null) {
            throw new InvalidParameterException("id不能为空");
        }
        hotelService.updateById(hotel);
    }

    /**
     * 按id删除
     *
     * @param id id
     */
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        hotelService.removeById(id);
    }
}
