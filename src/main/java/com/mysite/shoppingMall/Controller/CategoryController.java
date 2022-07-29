package com.mysite.shoppingMall.Controller;

import com.mysite.shoppingMall.Repository.CategoryRepository;
import com.mysite.shoppingMall.Ut.Ut;
import com.mysite.shoppingMall.Vo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    // C 생성 ==========================================================
    @RequestMapping("/doWrite")
    @ResponseBody
    public String doWrite(String name, String price, String desc){
        if(Ut.empty(name)){
            return "상품 이름을 입력하세요.";
        }
        if(Ut.empty(price)){
            return "상품 가격을 입력하세요";
        }
        if(Ut.empty(desc)){
            return "상품 상세정보를 입력하세요.";
        }

        Category category = new Category();
        category.setName(name);
        category.setPrice(price);
        category.setDesc(desc);

        categoryRepository.save(category);

        return "%d번 상품이 등록 되었습니다.".formatted(category.getId());
    }

    // R 읽기 ==========================================================

    @RequestMapping("/list")
    @ResponseBody
    public List<Category> showList(){
        return categoryRepository.findAll();
    }

    @RequestMapping("/detail")
    @ResponseBody
    public Category showDetail(Integer id){
        Category category = categoryRepository.findById(id).get();

        return category;
    }

    // U 수정 ==========================================================
    @RequestMapping("/doModify")
    @ResponseBody
    public String doModify(Integer id, String name, String price, String desc){
        if(id == null){
            return "상품 번호를 입력해주세요.";
        }
        if(Ut.empty(name)){
            return "상품 이름을 입력하세요.";
        }
        if(Ut.empty(price)){
            return "상품 가격을 입력하세요";
        }
        if(Ut.empty(desc)){
            return "상품 상세정보를 입력하세요.";
        }
        if(!categoryRepository.existsById(id)){
            return "상품 정보가 없습니다.";
        }

        Category category = categoryRepository.findById(id).get();

        category.setName(name);
        category.setPrice(price);
        category.setDesc(desc);

        categoryRepository.save(category);

        return "%d번 상품 수정이 완료되었습니다.".formatted(category.getId());
    }

    // D 삭제 ==========================================================
    @RequestMapping("/doDelete")
    @ResponseBody
    public String doDelete(Integer id, String name, String price, String desc){

        Optional<Category> opCategory = categoryRepository.findById(id);


        categoryRepository.delete(opCategory.get());

        return "%d번 게시물을 삭제했습니다.".formatted(id);
    }
}
