# capstoneproject
Capstone project in Department of Bigdata engineering , SoonChunHyang University

### 프로젝트 주제
비대면 투표의 공정성 문제와 낮은 참여율을 개선하기 위한 투표 앱 개발

### 사용방법
apk파일 다운로드

### 의존성
- Android Studio 4.1.1
- Java jdk 14 
- Firebase


### Activity별 작동 원리
#### 1. MainActivity
메인 화면입니다. 우측 하단에 있는 '+' 버튼으로 투표를 생성할 수 있는 CreateActivity로 이동할 수 있습니다.

입력창에 token을 입력한 뒤, [INSERT]버튼을 누르면 투표를 진행할 수 있는 SubActivity로 이동할 수 있습니다. 입력창에 token을 입력한 뒤, [SEARCH]버튼을 누르면 투표 관리자 전용 SearchActivity로 이동할 수 있습니다. 해당 화면에 투표 관리자 전용 token을 입력하면 아직 투표에 참여하지 않은 사람들의 명단을 확인할 수 있습니다.

#### 2. CreatActivity
투표 관리자(진행자)가 투표를 생성할 수 있는 화면입니다. 투표 참여 명단과 후보자(+공약)을 입력하면, CreatActivity3에서 투표 참여자 전용 token과 투표 관리자 전용 token을 받을 수 있습니다. 투표 관리자는 참여자 전용 token을, 투표 참여 명단에 있는 사람들에게 전달합니다. 이 token을 통해 투표에 참여할 수 있는 SubActivity로 이동할 수 있습니다.

※ CreatActivity3의 [FINISH]버튼을 누르지 않으면, 모든 정보를 입력했어도 투표는 개설되지 않습니다.

#### 3. SubActivity [INSERT]
투표 참여자가 투표에 참여할 수 있는 화면입니다. 투표 관리자로부터 받은 token을 입력하여 이동할 수 있습니다. SubActivity에서 자신의 학번(혹은 투표 참여 명단에 올라가있는 자신의 고유번호, 이름 등)을 입력했을 때, 투표 참여 명단에 올라가 있다면 투표를 진행하는 SubActivity3으로 넘어갈 수 있습니다.

※ 투표 관리자가 후보자의 공약까지 작성했다면 SubActivity2로 넘어가 후보자들의 공약을 확인 한 뒤, SubActivity3으로 넘어갑니다.

#### 4. SearchActivity [SEARCH]
투표 관리자가 아직 투표에 참여하지 않은 사람들의 명단을 확인할 수 있는 화면입니다. SearchActivity에서 투표 관리자 전용 token을 입력하면, 투표 미참여 명단을 Dialog로 띄워줍니다.

#### 5. SubFinalActivity
투표가 완료되었을 때, 파이차트를 이용하여 투표 결과를 보여주는 화면입니다. 참여 명단의 모든 인원이 투표를 완료했을 때 MainActivity에 token을 입력하고 [INSERT] 버튼을 누르면, SubActivity가 아닌, SubFinalActivity로 이동하게 됩니다.

### 라이센스
본 프로젝트는 학업/연구 목적, 혹은 비영리적인 목적으로 사용이 가능합니다.

전체적인 권한은 최강지민 jjimini98@naver.com 유노지희 user_11@naver.com 에게 있습니다.
