# javaWebDevelopmentWorkBookStudy

#개인 공부용입니다 

#자바웹개발워크북. 171109 학습
저자 엄진영. 

1. HTTP 응답상태코드. 

	상태코드 						상태설명
	 200	|	요청이 성공적으로 처리되었다.


	 301	|	요청한 자원이 이동되었다. 헤더 정보에 이동 위치를 알려줄테니 다시 요청하라 (redirect)


	 304	|	클라이언트가 임시 보관한 응답결과와 다르지 않다.


	 400 	|	잘못된 요청


	 404	|	요청한 자료를 못찾음(not found)


	 500	|	서버 내부에서 오류 발생		


2. JAVA EE와 Servlet/JSP 버전, 그리고 구현체 버전. 

	JAVAEE  	   Servlet			Tomcat		  JBoss				WebLogic		     	JEUS


	JAVAEE 6	 servlet 3.0		 7.0.x     7.x(all)      12.x           7.x
				   JSP 2.2


	JAVAEE 5	 servlet 3.0		 6.0.x     5.x 		     10.x            6.x
				   JSP 2.1


	J2EE 1.4 	 servlet 2.4		 5.5.x     4.x 		   9.x             5.x
			       JSP 2.0


	J2EE 1.3	 servlet 2.3		 4.1.x     			     7.x, 8.x           4.x
				   JSP 1.2


	J2EE 1.2	 servlet 2.2		 3.3.x     			        6.x             3.x
				   JSP 1.1



3.  웹 프로젝트 폴더 구조. 
		
		src 자바 소스 파일을 두는 폴더.  
	
	Build / classes. 

		컴파일된 자바 클래스 파일(.class)이 놓이는 폴더. 

	WebContent.  

		HTML, CSS, JS, JSP. 이미지 파일등 웹 콘텐츠 폴더. 

	WebContent  / WEB_INF. 

		웹 애플리케이션 설정과 관련 파일을 두는 폴더 이 폴더에 있는 파일은 클라이언트에서 요청할 수 없다.

	WebContent / WEB_INF / web.xml. 
	
		웹 애플리케이션 배치 설명서(Deplayment descriptor) 파일 줄여서 DD파일 이라고도 한다.	
		서블릿이나 필터, 리스너, 매개변수, 기본 웹 페이지 등 웹 애플리케이션 컴퍼논들의 배치 정보를 이 파일에 작성.  

4. Servlet 생명주기 

	Servlet LifeCycle 관련된 메소드는 init(), service(), destory()가 있다.

	init()는 서블릿 컨테이너가 서블릿을 생성한 후 초기화 작업을 수행하기 위해 호출하는 메소드

	서블릿이 클라이언트의 요청을 처리하기 전에 준비할 작업이 있다면 이 메서드에 작성 필요 (데이터베이스 연결, 외부 스토리지 서버와 연결, 프로퍼티 로딩 등)

	service()는 클라이언트가 요청할 때 마다 호출되는 메소드 즉 실질적으로 서비스 작업을 수행하는 메소드 

	destroy()는 서블릿 컨테이너가 종료되거나 웹 어플리케이션이 멈출 때 또는 해당 서블릿이 비활성 시킬 때 호출 

	추가로 getServletConfig(), GetServletinfo()가 더 있다.

	getServletConfig()는 서블릿 설정 정보를 다루는 Servlet config객체를 반환(이 객체를 통해 서블릿 이름과 매개변수 환경정보를 읽음)

	getServletInfo()는 서블릿을 작성한 사람에 대한 정보, 서블릿 버전, 관리등을 담는 문자를 반환 

	


