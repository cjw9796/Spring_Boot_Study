package com.kh.firstproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.firstproject.dto.ArticleForm;
import com.kh.firstproject.entity.Article;
import com.kh.firstproject.repository.ArticleRepository;

@Controller
public class ArticleController {

	//JPA  Repository 인터페이스 객체를 선언하고 @Autowired로 초기화한다. 
	// 스프링 부트가 미리 생성해놓은 객체를 가져다가 자동으로 연결한다. 
	@Autowired
	private ArticleRepository articleRepository;
	
	// 뷰 페이지 만들기 
	@GetMapping("/Articles/new")
	public String newArticleForm() {
		
		//롬복 테스트용~
		//ArticleForm a1 = new ArticleForm("dldl", "ddd");
		//System.out.println("테스트용"+a1.getTitle());
		
		
		return "/Articles/new"; 
	} 
	
	// form에서 넘어오는 데이터는 매핑 PostMapping()	
	@PostMapping("/Articles/create")
	public String createArticle(ArticleForm form) {
		System.out.println(form);
		
		//DTO의 데이터를 Entity로 변환한다. 
		Article article = form.toEntity();
		System.out.println(article);
		
		//Repository에게 Entity를 데이터베이스에 저장하게 한다. 
		//id가 자동으로 증가된다. 
		
		Article saved = articleRepository.save(article);
		System.out.println(saved);
		
		return "redirect:/Articles"; //목록보기 
		//return "Articles/new";
	}
	
	// return "/Articles/new";: 뷰를 찾아서 보여줌.
	// return "redirect:/Articles";: 다른 URL로 리다이렉션 시킴.
	//예를 들어, 사용자가 특정 기사를 작성하는 페이지로 이동하려고 할 때, 
	//return "/Articles/new";는 해당 페이지를 보여주고, 
	//return "redirect:/Articles";는 사용자가 기사 작성을 완료하고 난 뒤 
	//기사 목록이 있는 페이지로 리다이렉션하는데 사용될 수 있다.
	
	
	
	//브라우저에서 /Articles/글번호 형태의 요청을 받아서 처리한다.
	// {} /Articles/1, /Articles/2
	// {변수명}을 통해서 받는 데이터를 저장할 변수를 만들고 
	//url의 들어오는 값을 변수명으로 매칭시켜서 사용할 수 있도록 어노테이션을 사용한다. 
	
	@GetMapping("/Articles/{id}")
	public String show(@PathVariable Long id, Model model) {
		
		System.out.println("컨트롤러의 show()메서드 실행");
		System.out.println("id = " + id);
		
		// id 한 건 마다 해당되는 데이터를 테이블에서 가지고 온다. 
		// findById() id값을 넣어주면 테이블에서 찾아서 결과를 반환한다. 
		// 만약 데이터가 없다면 orElse(null) 메서드가 실행하면 null을 리턴시킨다.  
		
		Article articleEcntity = articleRepository.findById(id).orElse(null);
		
		//테이블에서 데이터를 가져와서 show.mustache 파일로 넘기기 위해서 model 인터페이스 객체에 넣어준다.
		
		model.addAttribute("article",articleEcntity);
		//뷰 페이지로 이동한다. 
		
		return "/Articles/show"; 
	}
	
	// 전체 글 보기
	@GetMapping("/Articles")
	public String index(Model model) {
		System.out.println("컨트롤러의index()메서드 실행");
		
		//테이블에 저장된 모든 글 목록을 읽어온다.
		List<Article> articleEntityList = articleRepository.findAll();
		System.out.println(articleEntityList);
		
		//가져온 글의 목록을 idex.mustache로 넘겨주기 위해서 model 객체에 저장한다. 
		model.addAttribute("articleList",articleEntityList);
		
		return"/Articles/index";
	}
	
	//글 번호를 가지고 수정하는 메서드 
	@GetMapping("/Articles/{id}/edit")
	public String edit(@PathVariable Long id, Model model) {
		System.out.println("컨트롤러의 edit() 메서드를 실행");
		System.out.println("id = " + id);

		// 수정할 데이터를 얻어온다.

		Article articleEcntity = articleRepository.findById(id).orElse(null);

		// 테이블에서 데이터를 가져와서 show.mustache 파일로 넘기기 위해서 model 인터페이스객체
		// 에 넣어준다.

		model.addAttribute("article", articleEcntity);
		//뷰 페이지로 이동한다. 
		return "/Articles/edit";
	}
	
	@PostMapping("/Articles/update")
	//form 에서 넘어오는 데이터 전체를 받기 위해서 커맨드 객체를 사용한다. 
	public String update(ArticleForm form) {
		
		System.out.println("컨트롤러 update() 메서드를 실행");
		System.out.println(form.toString());
		
		// DTO -> Entity 로 변환한다.
		Article article = form.toEntity();
		System.out.println(article.toString());
		
		//데이터베이스에 저장된 수정할 데이터를 얻어와서 Entity로 수정한 후 데이터베이스에
		// 저장한다.
		Article target = articleRepository.findById(article.getId()).orElse(null);
		
		if (target != null) {
			articleRepository.save(article);
		}
		
		//수정한 글 1건만 보여주고 싶을 때는 
		return "redirect:/Articles/" + article.getId();
		
		// 목록보기 return "redirect:/articles"
	}
	
	//글을 삭제하기 
	@GetMapping("/Articles/{id}/delete")
	public String delete(@PathVariable Long id, RedirectAttributes rttr) {
		
		System.out.println("컨트롤러 delete() 메서드를 실행");
		System.out.println("id: " + id);
		
		//삭제할 데이터를 가져온다. 
		Article target = articleRepository.findById(id).orElse(null);
		System.out.println(target.toString());
		
		//데이터 삭제 
		if(target != null) {
			articleRepository.delete(target);
			
			//메서드를 이용해서 1회성으로 사용할 메시지를 뷰 페이지로 전달한다. 
			rttr.addFlashAttribute("msg", id + "번 글 삭제 완료!");
		}
		
		
		return "redirect:/Articles/"; 
	}
	
	
	
	
	
	
}
