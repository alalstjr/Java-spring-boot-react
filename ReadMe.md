# Spring Boot React 연결하기

## 작업 실행 날짜 : 2019-06-08일 실시

## 작업 상태 : 2019-06-13일 완성

## 조건 spring, node(npm), mysql 설치

# 참조 자료

https://github.com/kantega/react-and-spring

https://github.com/search?p=1&q=springboot+react&ref=searchresults&type=Repositories&utf8=%E2%9C%93

## spring boot 생성 필수x
https://start.spring.io/

## 외국 유명 유튜버
https://www.youtube.com/watch?v=sHXBWHONoRs&list=PL_FFk2jKcxgpNhlT1aJ20yQPvxrEFWFLL&index=1
https://www.youtube.com/watch?v=ND1O-PXLYPk&list=PL_FFk2jKcxgpNhlT1aJ20yQPvxrEFWFLL&index=12

## 쭌프로 한마디
슬슬 포트폴리오 홈페이지를 제작해야하는 시기가 온거같습니다.
프론트엔드 개발자 경력이 있음으로 백엔드 개발자도 프론트 영역의 지식이 필요하다는 것을 알기 때문에
프론트엔드 React 라이브러리를 알고 있어야 현업에서 같이 개발이 수월할꺼 같아 적용시켜 만들어 보려고 합니다.

## 2019-06-11일 - 프로젝트 초기 설정

JPA - @Entity 엔티티 매핑
https://doublesprogramming.tistory.com/260

### @Id와 @GeneratedValue 애노테이션

<b>@Id</b>는 해당 프로퍼티가 테이블의 주키(primary key) 역할을 한다는 것을 나타낸다. 속성에 직접 @Id를 붙여주면 실행시점에 객체의 필드를 통해 직접 접근하게 하는 것이며, getter를 이용하려면 getter에 @Id를 붙여준다. 속성에부여하게 되면 setter/getter 없이도 작업이 가능하다. setter에 @Id를 붙이면 예외가 발생한다.

<b>@GeneratedValue</b>는 주키의 값을 위한 자동 생성 전략을 명시하는데 사용
선택적 속성으로 generator와 strategy가 있다. strategy는 persistence provider가 엔티티의 주키를 생성할 때 사용해야 하는 주키생성 전략을 의미한다. 디폴트 값은 AUTO이다. generator는 SequenceGenerator나 TableGenerator 애노테이션에서 명시된 주키 생성자를 재사용할 때 쓰인다. 디폴트 값은 공백문자("")이다. 

주키 생성 전략으로 JPA가 지원하는 것은 아래의 네 가지이다.
- AUTO : (persistence provider가) 특정 DB에 맞게 자동 선택
- IDENTITY : DB의 identity 컬럼을 이용
- SEQUENCE : DB의 시퀀스 컬럼을 이용
- TABLE : 유일성이 보장된 데이터베이스 테이블을 이용

postgres를 이용하여 테스트해보면 AUTO와 SEQUENCE는 실제 INSERT 쿼리가 일어나기 전에 다음 쿼리를 통해서 주키를 가져오는 것을 확인할 수 있다.
select nextval ('hibernate_sequence')

IDENTITY는 예외가 발생하고, TABLE의 경우 내부적으로 사용하는 것으로 추정할 수 있는 알 수 없는 값으로 id가 부여된다. AUTO 이외의 생성 전략을 사용할 경우 대상 DB에 대한 지식이 요구됨을 알 수 있다. 위의 네 가지 생성전략에 대한 hibernate의 내장 Generator 이름은 다음과 같다.
- native : AUTO
- identity : IDENTITY
- sequence : SEQUENCE
- TABLE에 대응하는 내장 Generator는 없음

이외에도 hibernate는 몇가지 생성 전략이 더 지원한다.

출처: https://jsaver.tistory.com/entry/Id와-GeneratedValue-애노테이션 [빗소리들으면서 개발하기..]

# H2 console 정의 및 설정

## package 생성

> src/main/java/(package) create

domain <br/>
repository(저장소) <br/>
web <br/>
service

## 1. Entity로 PROJECT_TASK 테이블 자동 생성하기

> src/main/java/domain(pack)/ProjectTask.java(class)

<a href="#none">ProjectTask.java 소스확인</a>

테이블 작성후 Spring Boot 실행후 H2 Console(http://localhost:8080/h2-console) 접속

![Description](https://raw.githubusercontent.com/alalstjr/Spring-boot-React/master/images/2019-06-12-1.png)

JDBC URL: jdbc:h2:mem:testdb <br/>
주의할 점은 JDBC URL 주소를 위의 주소로 작성해 주고 Connect 해주어야 합니다.

![Description](https://raw.githubusercontent.com/alalstjr/Spring-boot-React/master/images/2019-06-12-2.png)

정상적으로 테이블이 생성되었다면 성공한 것입니다.

출처: https://dodo4513.github.io/2018/04/01/spring_h2_init/

### ProjectTaskRepository 생성

DAO 라는 것을 알려주기 위해서 @Repository 를 작성해 줍니다.

출처 : https://m.blog.naver.com/scw0531/220988401816

> src/main/java/repository(pack)/ProjectTaskRepository.java(interface)

<a href="#none">ProjectTaskRepository.java 소스확인</a>

### ProjectTaskService 생성

> src/main/java/repository(pack)/ProjectTaskService.java(interface)

<a href="#none">ProjectTaskService.java 소스확인</a>

### ProjectTaskController 생성

<a href="#none">ProjectTaskService.java 소스확인</a>

## API 전송 테스트

정상적으로 값을 받아오는지 postmen 프로그램을 활용하여 확인합니다.

{% highlight matlab %}
  {
    "summary" : "new Project task",
    "acceptanceCriteria" : "create project board"
  }
{% endhighlight %}

![Description](https://raw.githubusercontent.com/alalstjr/Spring-boot-React/master/images/2019-06-12-3.png)
![Description](https://raw.githubusercontent.com/alalstjr/Spring-boot-React/master/images/2019-06-12-4.png)

정상적으로 접근하여 Database에 값을 추가한 것을 확인할 수 있습니다.

### 비정상적으로 접근할경우 @Valid를 통한 데이터 검증

![Description](https://raw.githubusercontent.com/alalstjr/Spring-boot-React/master/images/2019-06-12-5.png)

만약에 summary 값을 전송하지 않는다면 알기 어려운 오류문이 출력됩니다. <br/>
개발자 입장에서 무엇이 어디서 잘못된지 알아보기 어려울것입니다. <br/>
보기 어려운 오류 출력문을 개선하기 위해서 어노테이션 @Valid 을 활용하도록 하겠습니다.

> ProjectTaskController.java

{% highlight matlab %}
  	public ResponseEntity<?> addPTToBoard(@RequestBody ProjectTask projectTask) {
      ...(생략)
    }
    
    // @Valid 어노테이션을 addPTToBoard 매개변수에 추가합니다.
  	public ResponseEntity<?> addPTToBoard(@Valid @RequestBody ProjectTask projectTask) {
      ...(생략)
    }
{% endhighlight %}

![Description](https://raw.githubusercontent.com/alalstjr/Spring-boot-React/master/images/2019-06-12-6.png)

@Valid를 통한 데이터 검증하여
ERROR 상태와 어디서, 무엇이, 왜 ERROR 가 발생한것인지 알려주는 것을 확인할 수 있습니다.
개발자 입장에서 확실하게 ERROR 가 발생하는 곳을 찾아 고쳐서 프로그램을 더욱 클린하게 제작할 수 있습니다.

이제 오류는 출력하는 것을 확인했고 최종으로 
BindingResult 에 정보만 담아줄 뿐, Controller 는 그냥 진행이 되어버립니다.
Controller 에서 BindingResult 의 내용을 확인해서 오류가 없었는지 확인한 뒤에 코드가 진행되도록 해야 합니다.
(쭌피셜: 더욱 명확하게 오류가 무엇인지 해주는것 같습니다.)

{% highlight matlab %}
	public ResponseEntity<?> addPTToBoard(@Valid @RequestBody ProjectTask projectTask, BindingResult result) {
		
		if(result.hasErrors()) {
			Map<String, String> errorMap = new HashMap<String, String>();
			
			for(FieldError error : result.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			
			return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
		}
		
		ProjectTask newPT = projectTaskService.saveOrUpdateProjectTask(projectTask);
		
		return new ResponseEntity<ProjectTask>(newPT, HttpStatus.CREATED);
	}
{% endhighlight %}

![Description](https://raw.githubusercontent.com/alalstjr/Spring-boot-React/master/images/2019-06-12-7.png)

## 등록된 모든 테이블 자료 블러오기

ProjectTaskController.java 

{% highlight matlab %}
	@GetMapping("/all")
	public Iterable<ProjectTask> getAllPTs() {
	    return projectTaskService.findAll();
	}
{% endhighlight %}

생성하여 get 방식 주소로 all 접근시 service 에 있는 findAll 함수 실행 

![Description](https://raw.githubusercontent.com/alalstjr/Spring-boot-React/master/images/2019-06-12-8.png)

## 등록된 테이블 특정 자료 불러오기

ProjectTaskRepository 

{% highlight matlab %}
	ProjectTask getById(Long id);
{% endhighlight %}

ProjectTaskService

{% highlight matlab %}
	public ProjectTask findById(Long id) {
		return projectTaskRepository.getById(id);
	}
{% endhighlight %}

ProjectTaskController

{% highlight matlab %}
	@GetMapping("/{pt_id}")
	public ResponseEntity<?> getPTById(@PathVariable Long pt_id) {
		ProjectTask projectTask = projectTaskService.findById(pt_id);
		return new ResponseEntity<ProjectTask>(projectTask, HttpStatus.OK);
	}
{% endhighlight %}
	
## 등록된 테이블 특정 자료 지우기

ProjectTaskService

{% highlight matlab %}
	public void delete(Long id) {
		ProjectTask projectTask = findById(id);
		projectTaskRepository.delete(projectTask);
	}
{% endhighlight %}

ProjectTaskController

{% highlight matlab %}
	@DeleteMapping("/{pt_id}")
	public ResponseEntity<?> deleteProjectTask(@PathVariable Long pt_id) {
		projectTaskService.delete(pt_id);

		return new ResponseEntity<String>("Project Task delete", HttpStatus.OK);
	}
{% endhighlight %}

# React Install

> npm install create-react-app

> create-react-app {project name}

> npm install axios 
<a href="https://velopert.com/1552">axios 자세히</a>

> npm install -s-d bootstrap@4.1.3
> npm install -s-d redux react-redux redux-thunk react-router-dom

# @Valid 객체 검증
http://egloos.zum.com/springmvc/v/509029
https://lalwr.blogspot.com/2018/05/valid-bindingresult.html 스프링에서 검증 @Valid 와 BindingResult

# 어노테이션 정리

## @NotBlank
http://wonwoo.ml/index.php/post/520

# 개발시 API 테스트 프로그램
https://dpdpwl.tistory.com/71

# Spring Data JPA 를 이용한 DB 개발
http://www.libqa.com/wiki/730
