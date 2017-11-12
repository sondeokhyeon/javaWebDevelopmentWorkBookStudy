# javaWebDevelopmentWorkBookStudy

## 개인 공부용입니다

### 자바웹개발워크북. 171109 학습
저자 엄진영  

---
#### 1. HTTP 응답상태코드.

	상태코드 	|		상태설명
	 200	|	요청이 성공적으로 처리되었다.
	 301	|	요청한 자원이 이동되었다. 헤더 정보에 이동 위치를 알려줄테니 다시 요청하라 (redirect)
	 304	|	클라이언트가 임시 보관한 응답결과와 다르지 않다.
	 400    |	잘못된 요청
	 404	|	요청한 자료를 못찾음(not found)
	 500	|	서버 내부에서 오류 발생		


#### 2. JAVA EE와 Servlet/JSP 버전, 그리고 구현체 버전.

	JAVAEE  	|   Servlet	  |	Tomcat	|  JBos		   |	WebLogic	 |    JEUS

	JAVAEE 6	| servlet 3.0	|	 7.0.x   |  7.x(all)       |   12.x          |     7.x
				   JSP 2.2

	JAVAEE 5	| servlet 3.0	|	 6.0.x   |  5.x 	   |  10.x           |    6.x
				   JSP 2.1

	J2EE 1.4        | servlet 2.4	|	 5.5.x   | 4.x 	   	   | 9.x     	     |     5.x
			       JSP 2.0

	J2EE 1.3	| servlet 2.3	|	 4.1.x   |  		   | 7.x, 8.x        |  4.x
				   JSP 1.2

	J2EE 1.2	| servlet 2.2	|     3.3.x 	 |  	   	   |   6.x           |    3.x
				   JSP 1.1


#### 3.  웹 프로젝트 폴더 구조.

		src
		자바 소스 파일을 두는 폴더.  

	Build / classes.
		컴파일된 자바 클래스 파일(.class)이 놓이는 폴더.

	WebContent.  
		HTML, CSS, JS, JSP. 이미지 파일등 웹 콘텐츠 폴더.

	WebContent  / WEB_INF.
		웹 애플리케이션 설정과 관련 파일을 두는 폴더 이 폴더에 있는 파일은 클라이언트에서 요청할 수 없다.

	WebContent / WEB_INF / web.xml.
		웹 애플리케이션 배치 설명서(Deplayment descriptor) 파일 줄여서 DD파일 이라고도 한다.
		서블릿이나 필터, 리스너, 매개변수, 기본 웹 페이지 등 웹 애플리케이션 컴퍼논들의 배치 정보를
		이 파일에 작성.  

#### 4. Servlet 생명주기

	Servlet LifeCycle 관련된 메소드는 init(), service(), destory()가 있다.
	init()는 서블릿 컨테이너가 서블릿을 생성한 후 초기화 작업을 수행하기 위해 호출하는 메소드
	서블릿이 클라이언트의 요청을 처리하기 전에 준비할 작업이 있다면 이 메서드에 작성 필요 (데이터베이스 연결, 외부 스토리지 서버와 연결, 프로퍼티 로딩 등)
	service()는 클라이언트가 요청할 때 마다 호출되는 메소드 즉 실질적으로 서비스 작업을 수행하는 메소드
	destroy()는 서블릿 컨테이너가 종료되거나 웹 어플리케이션이 멈출 때 또는 해당 서블릿이 비활성 시킬 때 호출
	추가로 getServletConfig(), GetServletinfo()가 더 있다.
	getServletConfig()는 서블릿 설정 정보를 다루는 Servlet config객체를 반환(이 객체를 통해 서블릿 이름과 매개변수 환경정보를 읽음)
	getServletInfo()는 서블릿을 작성한 사람에 대한 정보, 서블릿 버전, 관리등을 담는 문자를 반환

#### 5. Servlet / JDBC (mysql)

	멤버 테이블 생성 및 데이터 인서트

		create table members (
			MNO integer not null comment '회원번호',
			EMAIL varchar(40) not null comment '이메일',
			PWD varchar(100) not null comment '암호',
			MNAME varchar(50) not null comment '이름',
			cre_date datetime not null comment '가입일',
			mod_date datetime not null comment '마지막암호변경일'
		)

		alter table members
			add constraint pk_members
					primary key (
						mno
					);

		create unique index UIX_members
			on members (
					EMAIL ASC
			);

		alter table members
			modify column mno integer not null auto_increment
			comment '회원일련번호';

	 		insert into members (email,pwd,mname,cre_date,mod_date) values ('s1@test.com',123,'홍길동',now(),now());
	 		insert into members (email,pwd,mname,cre_date,mod_date) values ('s2@test.com',123,'임꺽정',now(),now());
	 		insert into members (email,pwd,mname,cre_date,mod_date) values ('s3@test.com',123,'일지매',now(),now());
	 		insert into members (email,pwd,mname,cre_date,mod_date) values ('s4@test.com',123,'이몽룡',now(),now());
	 		insert into members (email,pwd,mname,cre_date,mod_date) values ('s5@test.com',123,'성춘향',now(),now());

			실습
			먼저 Connection과 prepareStatement를 준비한다
			그 이후 jdbc드라이버를 로딩 한다 (DriverManager.registerDriver를 이용)
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
																	(패키지명까지 입력해야 한다)

			그 후 Connection으로 준비된 자원을 통하여 JDBC URL, 접속할 계정,
			그리고 비밀번호를 통하여 Connection을 만들어준 후
			prepareStatement를 통해 쿼리를 날린다

			prepareStatement는 쿼리를 미리 준비한 자원이므로
			INSERT INTO MEMBERS(EMAIL, PWD, MNAME,CRE_DATE, MOD_DATE)"
					+ "VALUES (?,?,?,NOW(),NOW())")
			(? ? ?)에 setString을 통하여 동적으로 데이터를 입력한다
			(반복문을 통한다면 데이터 입력이 더 수월할것으로 보인다.)

			---
			JDBC URL의 주소
		 	jdbc:mysql:thin:@localhost:3306:studydb

		 	'jdbc:mysql' = 사용할 JDBC 드라이버  
		 	':thin:' = 드라이버 타입
		 	'@localhost' = 서버주소
		 	':3306'= 포트번호
		 	'studydb' = DB 서비스 아이디
			---

			DD파일(Web.xml)에 서블릿 초기화 매개변수 설정

			DD파일에는 URL매핑 뿐이 아니라 아래와 같이 서블릿에서 쓸 자원을 미리 준비할 수도 있다.
			<servlet>
				<servlet-name>서블릿 이름</servlet-name>
				<servlet-class>실제 경로(패키지명.클래스명)</servlet-class>
				<init-param>
						<param-name>driver</param-name>
						<param-value>com.mysql.jdbc.Driver</param-value>
				</init-param>
				<init-param>
						<param-name>url</param-name>
						<param-value>jdbc:mysql://localhost:studydb</param-value>
				</init-param>
				<init-param>
					<param-name>username</param-name>
					<param-value>study</param-value>
				</init-param>
				<init-param>
					<param-name>password</param-name>
					<param-value>1111</param-value>
				</init-param>
			</servlet>

			사용법은 this.getInitParameter("param-name"); 으로 가져오는게 가능하다
			또한 class 내부에서도 애노테이션을 통해서 준비가 가능한데 class 내부에서의
			자원 준비는 아래와같이 준비가 가능하다
			@WebServlet(
					URLPatterns = {"URL패턴"}
					@WebInitParam(name="키" value="벨류")
				)

			위의 방법들로 자원 준비에는 단점이 서블릿당 하나씩 자원을 준비해야한다
			모든 서블릿이 자원을 쓰기위해선 servlt-context로 준비해야 한다.

			<context-param>
				<param-name>키</param-name>
				<param-value>벨류</param-value>
			</context-param>

			방법은 위와 같이 코드를 DD파일에 입력할 경우 서블릿에서 아래와 같이 사용이 가능하다

			servlt-context sc = this.getServletContext();
			sc.getInitParameter("key");
