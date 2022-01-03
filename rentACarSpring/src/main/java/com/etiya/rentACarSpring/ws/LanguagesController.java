package com.etiya.rentACarSpring.ws;

import com.etiya.rentACarSpring.business.abstracts.LanguageService;
import com.etiya.rentACarSpring.business.dtos.LanguageSearchListDto;
import com.etiya.rentACarSpring.business.requests.create.CreateLanguageRequest;
import com.etiya.rentACarSpring.business.requests.delete.DeleteLanguageRequest;
import com.etiya.rentACarSpring.business.requests.update.UpdateLanguageRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/languages")
public class LanguagesController {
    private LanguageService languageService;

    @Autowired
    public LanguagesController(LanguageService languageService) {
        this.languageService = languageService;
    }



    @PostMapping("add")
    public Result add(@RequestBody @Valid CreateLanguageRequest createLanguageRequest){
        return this.languageService.add(createLanguageRequest);
    }

    @PutMapping("update")
    public Result add(@RequestBody @Valid UpdateLanguageRequest updateLanguageRequest){
        return this.languageService.update(updateLanguageRequest);
    }

    @DeleteMapping("delete")
    public Result add(@RequestBody @Valid DeleteLanguageRequest deleteLanguageRequest){
        return this.languageService.delete(deleteLanguageRequest);
    }

    @GetMapping("all")
    public DataResult<List<LanguageSearchListDto>> getAll(){
        return this.languageService.getAll();
    }


}
