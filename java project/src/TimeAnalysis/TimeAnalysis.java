package TimeAnalysis;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class TimeAnalysis {
	public static void main(String[] args)
	{
		final int TOPK=10;
		String inputWord = args[0];
		String groupName  =args[1];
		String docType = args[2];
		String dataPath = args[3];
		String jarPath = args[4];
		
		ArrayList<ArrayList<String>> lists = new ArrayList<ArrayList<String>>();
	
		try {
			
			String [] years={
					"0910",
					"1112",
					"1314"
					};
			
//			String [] texts = {"[{\"score\":\"0.44042843385816616\",\"rank\":1,\"keyword\":\"결정자\"},{\"score\":\"0.41662238467171026\",\"rank\":2,\"keyword\":\"다요소\"},{\"score\":\"0.4079111749663062\",\"rank\":3,\"keyword\":\"직관\"},{\"score\":\"0.3704470125378862\",\"rank\":4,\"keyword\":\"설계자\"},{\"score\":\"0.3701065509256217\",\"rank\":5,\"keyword\":\"다기준\"},{\"score\":\"0.35775305626371917\",\"rank\":6,\"keyword\":\"부지급\"},{\"score\":\"0.35308026429456807\",\"rank\":7,\"keyword\":\"선택\"},{\"score\":\"0.34542790356696507\",\"rank\":8,\"keyword\":\"암묵\"},{\"score\":\"0.3441826071064416\",\"rank\":9,\"keyword\":\"경영자\"},{\"score\":\"0.34143744677619475\",\"rank\":10,\"keyword\":\"과정\"},{\"score\":\"0.3393886970075026\",\"rank\":11,\"keyword\":\"정확\"},{\"score\":\"0.3340897845852773\",\"rank\":12,\"keyword\":\"투자자\"},{\"score\":\"0.33053816725894186\",\"rank\":13,\"keyword\":\"조언\"},{\"score\":\"0.3260370422167614\",\"rank\":14,\"keyword\":\"전문가\"},{\"score\":\"0.322389945875701\",\"rank\":15,\"keyword\":\"다목적\"},{\"score\":\"0.32064408776137127\",\"rank\":16,\"keyword\":\"결정권자\"},{\"score\":\"0.31594547408290075\",\"rank\":17,\"keyword\":\"다속\"},{\"score\":\"0.31416412608195554\",\"rank\":18,\"keyword\":\"합리\"},{\"score\":\"0.31285339067837287\",\"rank\":19,\"keyword\":\"의견\"},{\"score\":\"0.31170894949824807\",\"rank\":20,\"keyword\":\"취소\"},{\"score\":\"0.3107588797181064\",\"rank\":21,\"keyword\":\"지휘관\"},{\"score\":\"0.30705134855467336\",\"rank\":22,\"keyword\":\"실물\"},{\"score\":\"0.2982760259656984\",\"rank\":23,\"keyword\":\"지식\"},{\"score\":\"0.29694644745659626\",\"rank\":24,\"keyword\":\"여신\"},{\"score\":\"0.29531437390814386\",\"rank\":25,\"keyword\":\"신중\"},{\"score\":\"0.29405848500236986\",\"rank\":26,\"keyword\":\"결정법\"},{\"score\":\"0.2936094987844978\",\"rank\":27,\"keyword\":\"설명\"},{\"score\":\"0.29189939901193046\",\"rank\":28,\"keyword\":\"다구찌\"},{\"score\":\"0.2908050696561102\",\"rank\":29,\"keyword\":\"분석가\"},{\"score\":\"0.2901878936825222\",\"rank\":30,\"keyword\":\"평가자\"},{\"score\":\"0.2898042960894474\",\"rank\":31,\"keyword\":\"이해도\"},{\"score\":\"0.2879054944131437\",\"rank\":32,\"keyword\":\"근로복지공단\"},{\"score\":\"0.2871556728879324\",\"rank\":33,\"keyword\":\"코칭\"},{\"score\":\"0.2859724031665829\",\"rank\":34,\"keyword\":\"자질\"},{\"score\":\"0.28400126587871727\",\"rank\":35,\"keyword\":\"스킬\"},{\"score\":\"0.28355880877414386\",\"rank\":36,\"keyword\":\"이해관계자\"},{\"score\":\"0.28252124254376326\",\"rank\":37,\"keyword\":\"파레토\"},{\"score\":\"0.28246027431826454\",\"rank\":38,\"keyword\":\"대안\"},{\"score\":\"0.27903932864620795\",\"rank\":39,\"keyword\":\"기록부\"},{\"score\":\"0.2772175796238047\",\"rank\":40,\"keyword\":\"대화자\"},{\"score\":\"0.2768526906123452\",\"rank\":41,\"keyword\":\"의상\"},{\"score\":\"0.27591915426682834\",\"rank\":42,\"keyword\":\"항해사\"},{\"score\":\"0.27591779087381674\",\"rank\":43,\"keyword\":\"제시\"},{\"score\":\"0.27532163906162227\",\"rank\":44,\"keyword\":\"모형\"},{\"score\":\"0.274971636558563\",\"rank\":45,\"keyword\":\"실제\"},{\"score\":\"0.27424766296498143\",\"rank\":46,\"keyword\":\"최선\"},{\"score\":\"0.2742173392925743\",\"rank\":47,\"keyword\":\"성\"},{\"score\":\"0.2734037613704105\",\"rank\":48,\"keyword\":\"숙련\"},{\"score\":\"0.2728844569430285\",\"rank\":49,\"keyword\":\"연산자\"},{\"score\":\"0.2727254393982718\",\"rank\":50,\"keyword\":\"결과물\"},{\"score\":\"0.2720816530650961\",\"rank\":51,\"keyword\":\"여부\"},{\"score\":\"0.27205204829524365\",\"rank\":52,\"keyword\":\"경영\"},{\"score\":\"0.2717817930945027\",\"rank\":53,\"keyword\":\"적시\"},{\"score\":\"0.27065296995807453\",\"rank\":54,\"keyword\":\"합의\"},{\"score\":\"0.2701491052225093\",\"rank\":55,\"keyword\":\"조화색\"},{\"score\":\"0.26903589471666983\",\"rank\":56,\"keyword\":\"도움\"},{\"score\":\"0.2688850490537813\",\"rank\":57,\"keyword\":\"리스크\"},{\"score\":\"0.2676894134367091\",\"rank\":58,\"keyword\":\"부진아\"},{\"score\":\"0.26753435170789264\",\"rank\":59,\"keyword\":\"트리\"},{\"score\":\"0.2671171892791764\",\"rank\":60,\"keyword\":\"운영자\"},{\"score\":\"0.2670053909722545\",\"rank\":61,\"keyword\":\"공급사\"},{\"score\":\"0.2665431524740732\",\"rank\":62,\"keyword\":\"전문의\"},{\"score\":\"0.26644595926082415\",\"rank\":63,\"keyword\":\"족부\"},{\"score\":\"0.26542546067351097\",\"rank\":64,\"keyword\":\"선호도\"},{\"score\":\"0.26534104708319367\",\"rank\":65,\"keyword\":\"견해\"},{\"score\":\"0.26423029246174146\",\"rank\":66,\"keyword\":\"주변인\"},{\"score\":\"0.26399115841559595\",\"rank\":67,\"keyword\":\"간호조무사\"},{\"score\":\"0.2637909040713307\",\"rank\":68,\"keyword\":\"공급자\"},{\"score\":\"0.26332639289050447\",\"rank\":69,\"keyword\":\"지인\"},{\"score\":\"0.26307971896223376\",\"rank\":70,\"keyword\":\"스탭\"},{\"score\":\"0.2623233993209101\",\"rank\":71,\"keyword\":\"기획자\"},{\"score\":\"0.2607904362180999\",\"rank\":72,\"keyword\":\"기종\"},{\"score\":\"0.2588045691601645\",\"rank\":73,\"keyword\":\"진료\"},{\"score\":\"0.25739635522916937\",\"rank\":74,\"keyword\":\"지원\"},{\"score\":\"0.2573610314426087\",\"rank\":75,\"keyword\":\"수\"},{\"score\":\"0.25728615096127094\",\"rank\":76,\"keyword\":\"매매\"},{\"score\":\"0.2566772165641199\",\"rank\":77,\"keyword\":\"최적해\"},{\"score\":\"0.25640863069505937\",\"rank\":78,\"keyword\":\"방법\"},{\"score\":\"0.2557626834949835\",\"rank\":79,\"keyword\":\"하향식\"},{\"score\":\"0.2549036275780302\",\"rank\":80,\"keyword\":\"낙찰\"},{\"score\":\"0.25355535655542855\",\"rank\":81,\"keyword\":\"한의사\"},{\"score\":\"0.25326740015597454\",\"rank\":82,\"keyword\":\"퍼지\"},{\"score\":\"0.25160280569432714\",\"rank\":83,\"keyword\":\"도구\"},{\"score\":\"0.25144697146959427\",\"rank\":84,\"keyword\":\"계획가\"},{\"score\":\"0.2506845436787863\",\"rank\":85,\"keyword\":\"일관\"},{\"score\":\"0.2504644393775829\",\"rank\":86,\"keyword\":\"조율\"},{\"score\":\"0.25033596058563123\",\"rank\":87,\"keyword\":\"러프\"},{\"score\":\"0.24971153495273551\",\"rank\":88,\"keyword\":\"조종사\"},{\"score\":\"0.2495106873127849\",\"rank\":89,\"keyword\":\"추천\"},{\"score\":\"0.24946499048410425\",\"rank\":90,\"keyword\":\"예시\"},{\"score\":\"0.2492683147686254\",\"rank\":91,\"keyword\":\"반영\"},{\"score\":\"0.24925276045028166\",\"rank\":92,\"keyword\":\"일치군\"},{\"score\":\"0.24905844269957747\",\"rank\":93,\"keyword\":\"프로세스\"},{\"score\":\"0.24904922115955402\",\"rank\":94,\"keyword\":\"앙상\"},{\"score\":\"0.24819574002190187\",\"rank\":95,\"keyword\":\"답변\"},{\"score\":\"0.2481343185551212\",\"rank\":96,\"keyword\":\"혈우병\"},{\"score\":\"0.2476428913871142\",\"rank\":97,\"keyword\":\"기획\"},{\"score\":\"0.24590093094262755\",\"rank\":98,\"keyword\":\"법원\"},{\"score\":\"0.24561657956712518\",\"rank\":99,\"keyword\":\"도움말\"}]",
//					"[{\"score\":\"0.7267349600307919\",\"rank\":1,\"keyword\":\"결정자\"},{\"score\":\"0.617664672986601\",\"rank\":2,\"keyword\":\"투자자\"},{\"score\":\"0.5927839472897227\",\"rank\":3,\"keyword\":\"앙상블\"},{\"score\":\"0.5744081988095907\",\"rank\":4,\"keyword\":\"도움\"},{\"score\":\"0.5703278754180203\",\"rank\":5,\"keyword\":\"나무\"},{\"score\":\"0.5634905005763546\",\"rank\":6,\"keyword\":\"합리\"},{\"score\":\"0.5542721024718812\",\"rank\":7,\"keyword\":\"데이터마이닝\"},{\"score\":\"0.5530667938778122\",\"rank\":8,\"keyword\":\"트레이딩\"},{\"score\":\"0.546789232056143\",\"rank\":9,\"keyword\":\"결정권자\"},{\"score\":\"0.5422734495464808\",\"rank\":10,\"keyword\":\"주식\"},{\"score\":\"0.5394334028165253\",\"rank\":11,\"keyword\":\"선택\"},{\"score\":\"0.5375201671054993\",\"rank\":12,\"keyword\":\"신경망\"},{\"score\":\"0.5302446418255788\",\"rank\":13,\"keyword\":\"\"},{\"score\":\"0.5168087200548568\",\"rank\":14,\"keyword\":\"다기준\"},{\"score\":\"0.5160544918774811\",\"rank\":15,\"keyword\":\"선물\"},{\"score\":\"0.5137315719330572\",\"rank\":16,\"keyword\":\"원천\"},{\"score\":\"0.4904798543096918\",\"rank\":17,\"keyword\":\"위생사\"},{\"score\":\"0.48758858685360523\",\"rank\":18,\"keyword\":\"투자\"},{\"score\":\"0.485663603066889\",\"rank\":19,\"keyword\":\"분류기\"},{\"score\":\"0.47715163471485805\",\"rank\":20,\"keyword\":\"응급실\"},{\"score\":\"0.4764317615657838\",\"rank\":21,\"keyword\":\"크루즈\"},{\"score\":\"0.47448436197465843\",\"rank\":22,\"keyword\":\"휴리스틱\"},{\"score\":\"0.47366464491519517\",\"rank\":23,\"keyword\":\"트리\"},{\"score\":\"0.4722706526048868\",\"rank\":24,\"keyword\":\"증자\"},{\"score\":\"0.47128016857093036\",\"rank\":25,\"keyword\":\"주가\"},{\"score\":\"0.4711881986176971\",\"rank\":26,\"keyword\":\"매매\"},{\"score\":\"0.46896979131822536\",\"rank\":27,\"keyword\":\"외재\"},{\"score\":\"0.4665647998811673\",\"rank\":28,\"keyword\":\"성숙\"},{\"score\":\"0.4654257079261871\",\"rank\":29,\"keyword\":\"간과\"},{\"score\":\"0.4626638203994736\",\"rank\":30,\"keyword\":\"유상\"},{\"score\":\"0.4550087923210544\",\"rank\":31,\"keyword\":\"흥행\"},{\"score\":\"0.4532242805838666\",\"rank\":32,\"keyword\":\"마코프\"},{\"score\":\"0.45047398949372475\",\"rank\":33,\"keyword\":\"유용\"},{\"score\":\"0.4384087173242359\",\"rank\":34,\"keyword\":\"운영자\"},{\"score\":\"0.4378165009808606\",\"rank\":35,\"keyword\":\"마이닝\"},{\"score\":\"0.4377807171322373\",\"rank\":36,\"keyword\":\"복수\"},{\"score\":\"0.4321309254522472\",\"rank\":37,\"keyword\":\"신중\"},{\"score\":\"0.42072949697729667\",\"rank\":38,\"keyword\":\"과거\"},{\"score\":\"0.4185611069297757\",\"rank\":39,\"keyword\":\"영화\"},{\"score\":\"0.4106527977228404\",\"rank\":40,\"keyword\":\"옵션\"},{\"score\":\"0.40928215398006856\",\"rank\":41,\"keyword\":\"암묵\"},{\"score\":\"0.408026294430632\",\"rank\":42,\"keyword\":\"구매자\"},{\"score\":\"0.4067965445079774\",\"rank\":43,\"keyword\":\"이익\"},{\"score\":\"0.4057917373262031\",\"rank\":44,\"keyword\":\"수자원\"},{\"score\":\"0.40462219478914546\",\"rank\":45,\"keyword\":\"예측\"},{\"score\":\"0.4039546017479949\",\"rank\":46,\"keyword\":\"이진\"},{\"score\":\"0.39951984213036523\",\"rank\":47,\"keyword\":\"순위\"},{\"score\":\"0.39926009725197176\",\"rank\":48,\"keyword\":\"정당방위\"},{\"score\":\"0.39844770270618074\",\"rank\":49,\"keyword\":\"견해\"},{\"score\":\"0.39770309590643965\",\"rank\":50,\"keyword\":\"샘플\"},{\"score\":\"0.38944413478620127\",\"rank\":51,\"keyword\":\"실물\"},{\"score\":\"0.38902856658459467\",\"rank\":52,\"keyword\":\"대안\"},{\"score\":\"0.38837939394611765\",\"rank\":53,\"keyword\":\"다항로짓\"},{\"score\":\"0.3876956040525685\",\"rank\":54,\"keyword\":\"전작\"},{\"score\":\"0.3796726427427503\",\"rank\":55,\"keyword\":\"배차\"},{\"score\":\"0.37672834130348964\",\"rank\":56,\"keyword\":\"부\"},{\"score\":\"0.37641260534118093\",\"rank\":57,\"keyword\":\"매칭\"},{\"score\":\"0.37584069636440515\",\"rank\":58,\"keyword\":\"할인\"},{\"score\":\"0.3753695372406205\",\"rank\":59,\"keyword\":\"경영자\"},{\"score\":\"0.3745822164190911\",\"rank\":60,\"keyword\":\"우선순위\"},{\"score\":\"0.3706414054978597\",\"rank\":61,\"keyword\":\"이해관계자\"},{\"score\":\"0.3697687499815475\",\"rank\":62,\"keyword\":\"분류자\"},{\"score\":\"0.36948403862718615\",\"rank\":63,\"keyword\":\"로지스틱\"},{\"score\":\"0.3644133999277695\",\"rank\":64,\"keyword\":\"타인\"},{\"score\":\"0.3640725492449635\",\"rank\":65,\"keyword\":\"상충\"},{\"score\":\"0.3638055904521635\",\"rank\":66,\"keyword\":\"진로\"},{\"score\":\"0.3635442960771459\",\"rank\":67,\"keyword\":\"사이즈\"},{\"score\":\"0.362841001653172\",\"rank\":68,\"keyword\":\"수익\"},{\"score\":\"0.3620433579858532\",\"rank\":69,\"keyword\":\"백두대간\"},{\"score\":\"0.3605279736226859\",\"rank\":70,\"keyword\":\"구매\"},{\"score\":\"0.35985386426067745\",\"rank\":71,\"keyword\":\"지명\"},{\"score\":\"0.35937298829162684\",\"rank\":72,\"keyword\":\"판매자\"},{\"score\":\"0.3590563073243887\",\"rank\":73,\"keyword\":\"결합\"},{\"score\":\"0.35590834687589046\",\"rank\":74,\"keyword\":\"유망\"},{\"score\":\"0.35577277368490123\",\"rank\":75,\"keyword\":\"부속\"},{\"score\":\"0.35519359156978\",\"rank\":76,\"keyword\":\"보상\"},{\"score\":\"0.3541123391065554\",\"rank\":77,\"keyword\":\"우회\"},{\"score\":\"0.35359403739619716\",\"rank\":78,\"keyword\":\"변량\"},{\"score\":\"0.3535849006557671\",\"rank\":79,\"keyword\":\"객관\"},{\"score\":\"0.3533073357221852\",\"rank\":80,\"keyword\":\"지인\"},{\"score\":\"0.3513155588756688\",\"rank\":81,\"keyword\":\"신용\"},{\"score\":\"0.35016677673478913\",\"rank\":82,\"keyword\":\"연구자\"},{\"score\":\"0.35002744419913123\",\"rank\":83,\"keyword\":\"장바구니\"},{\"score\":\"0.3500121306628476\",\"rank\":84,\"keyword\":\"고취\"},{\"score\":\"0.34784134119225674\",\"rank\":85,\"keyword\":\"도덕\"},{\"score\":\"0.34773205874810775\",\"rank\":86,\"keyword\":\"우위\"},{\"score\":\"0.3470864234351359\",\"rank\":87,\"keyword\":\"베이즈\"},{\"score\":\"0.34676330600296096\",\"rank\":88,\"keyword\":\"재무\"},{\"score\":\"0.34547558233541537\",\"rank\":89,\"keyword\":\"규칙\"},{\"score\":\"0.3452935216859682\",\"rank\":90,\"keyword\":\"컨벤션\"},{\"score\":\"0.345139784487895\",\"rank\":91,\"keyword\":\"발굴\"},{\"score\":\"0.3435977864481627\",\"rank\":92,\"keyword\":\"결과물\"},{\"score\":\"0.343092324350039\",\"rank\":93,\"keyword\":\"페이지\"},{\"score\":\"0.34167188812301963\",\"rank\":94,\"keyword\":\"말기\"},{\"score\":\"0.33669233231341095\",\"rank\":95,\"keyword\":\"물건\"},{\"score\":\"0.3343053089551604\",\"rank\":96,\"keyword\":\"등급\"},{\"score\":\"0.33400741285922836\",\"rank\":97,\"keyword\":\"기회\"},{\"score\":\"0.3324414741515752\",\"rank\":98,\"keyword\":\"보유\"},{\"score\":\"0.3323664552339818\",\"rank\":99,\"keyword\":\"부가\"}]",
//					"[{\"score\":\"0.6735843904248987\",\"rank\":1,\"keyword\":\"결정자\"},{\"score\":\"0.6565585903920749\",\"rank\":2,\"keyword\":\"직관\"},{\"score\":\"0.5660923889597455\",\"rank\":3,\"keyword\":\"조언\"},{\"score\":\"0.5649283752695873\",\"rank\":4,\"keyword\":\"다기준\"},{\"score\":\"0.5340281501597924\",\"rank\":5,\"keyword\":\"개요\"},{\"score\":\"0.5337825002079327\",\"rank\":6,\"keyword\":\"심리학\"},{\"score\":\"0.5281928641370214\",\"rank\":7,\"keyword\":\"합리\"},{\"score\":\"0.5220051365652408\",\"rank\":8,\"keyword\":\"스킬\"},{\"score\":\"0.506779335596309\",\"rank\":9,\"keyword\":\"의존\"},{\"score\":\"0.5013753029776405\",\"rank\":10,\"keyword\":\"다목적\"},{\"score\":\"0.49805402736378435\",\"rank\":11,\"keyword\":\"나무\"},{\"score\":\"0.48204473952019067\",\"rank\":12,\"keyword\":\"결과물\"},{\"score\":\"0.4813196117129192\",\"rank\":13,\"keyword\":\"의견\"},{\"score\":\"0.4764183726905406\",\"rank\":14,\"keyword\":\"선택\"},{\"score\":\"0.45775734305030735\",\"rank\":15,\"keyword\":\"멘탈\"},{\"score\":\"0.45578106935390117\",\"rank\":16,\"keyword\":\"코칭\"},{\"score\":\"0.4474803933497834\",\"rank\":17,\"keyword\":\"예시\"},{\"score\":\"0.44716767807407626\",\"rank\":18,\"keyword\":\"정확\"},{\"score\":\"0.4418324193261012\",\"rank\":19,\"keyword\":\"인텔리전스\"},{\"score\":\"0.439973704505602\",\"rank\":20,\"keyword\":\"추천\"},{\"score\":\"0.43643983225491617\",\"rank\":21,\"keyword\":\"모형\"},{\"score\":\"0.43349067067501773\",\"rank\":22,\"keyword\":\"글쓰기\"},{\"score\":\"0.43299552222466614\",\"rank\":23,\"keyword\":\"오피니언\"},{\"score\":\"0.4328524185705229\",\"rank\":24,\"keyword\":\"미용\"},{\"score\":\"0.4195735502014314\",\"rank\":25,\"keyword\":\"도움\"},{\"score\":\"0.41818819310461214\",\"rank\":26,\"keyword\":\"이해\"},{\"score\":\"0.4179145568281205\",\"rank\":27,\"keyword\":\"담당자\"},{\"score\":\"0.41483172697468507\",\"rank\":28,\"keyword\":\"경청\"},{\"score\":\"0.4124920615615764\",\"rank\":29,\"keyword\":\"부도\"},{\"score\":\"0.41078523478721757\",\"rank\":30,\"keyword\":\"코치\"},{\"score\":\"0.40978571832999533\",\"rank\":31,\"keyword\":\"직종\"},{\"score\":\"0.40865689237329145\",\"rank\":32,\"keyword\":\"신경망\"},{\"score\":\"0.4084019652236223\",\"rank\":33,\"keyword\":\"협동\"},{\"score\":\"0.40832498288789426\",\"rank\":34,\"keyword\":\"퍼지\"},{\"score\":\"0.4077457932564374\",\"rank\":35,\"keyword\":\"프로세스\"},{\"score\":\"0.4071342977055732\",\"rank\":36,\"keyword\":\"진로\"},{\"score\":\"0.4068355295939912\",\"rank\":37,\"keyword\":\"논증\"},{\"score\":\"0.40502409468297385\",\"rank\":38,\"keyword\":\"지휘관\"},{\"score\":\"0.40429782180707896\",\"rank\":39,\"keyword\":\"묶음\"},{\"score\":\"0.403611078277618\",\"rank\":40,\"keyword\":\"자기표현\"},{\"score\":\"0.40017855692988463\",\"rank\":41,\"keyword\":\"파레토\"},{\"score\":\"0.4000842938922685\",\"rank\":42,\"keyword\":\"진실\"},{\"score\":\"0.39270268733714775\",\"rank\":43,\"keyword\":\"윤리\"},{\"score\":\"0.39162148051344214\",\"rank\":44,\"keyword\":\"반성\"},{\"score\":\"0.39112037374602754\",\"rank\":45,\"keyword\":\"개체명\"},{\"score\":\"0.38891072821080286\",\"rank\":46,\"keyword\":\"일관\"},{\"score\":\"0.3869329444417855\",\"rank\":47,\"keyword\":\"객관\"},{\"score\":\"0.385682602256036\",\"rank\":48,\"keyword\":\"모델\"},{\"score\":\"0.3823195294329558\",\"rank\":49,\"keyword\":\"안내\"},{\"score\":\"0.3816500774284933\",\"rank\":50,\"keyword\":\"신속\"},{\"score\":\"0.3814157277199586\",\"rank\":51,\"keyword\":\"포레스트\"},{\"score\":\"0.3770313394884327\",\"rank\":52,\"keyword\":\"컨설턴트\"},{\"score\":\"0.37608566829922535\",\"rank\":53,\"keyword\":\"트리\"},{\"score\":\"0.3758140814039295\",\"rank\":54,\"keyword\":\"교재\"},{\"score\":\"0.3733554190827713\",\"rank\":55,\"keyword\":\"친절\"},{\"score\":\"0.37315196369147074\",\"rank\":56,\"keyword\":\"타인\"},{\"score\":\"0.37252056758091207\",\"rank\":57,\"keyword\":\"서포트\"},{\"score\":\"0.37225387651411823\",\"rank\":58,\"keyword\":\"가치관\"},{\"score\":\"0.37149015282899894\",\"rank\":59,\"keyword\":\"이해관계자\"},{\"score\":\"0.37038665094208684\",\"rank\":60,\"keyword\":\"경영\"},{\"score\":\"0.37034283922780187\",\"rank\":61,\"keyword\":\"노무현\"},{\"score\":\"0.3698966504836275\",\"rank\":62,\"keyword\":\"결정법\"},{\"score\":\"0.3689679729298739\",\"rank\":63,\"keyword\":\"뇌출혈\"},{\"score\":\"0.36830271357428895\",\"rank\":64,\"keyword\":\"당사자\"},{\"score\":\"0.36804706862121467\",\"rank\":65,\"keyword\":\"이항\"},{\"score\":\"0.36626860591029203\",\"rank\":66,\"keyword\":\"설득\"},{\"score\":\"0.36625679660338994\",\"rank\":67,\"keyword\":\"마이닝\"},{\"score\":\"0.36602781801023365\",\"rank\":68,\"keyword\":\"접근법\"},{\"score\":\"0.36435975773622165\",\"rank\":69,\"keyword\":\"융\"},{\"score\":\"0.36355484124304444\",\"rank\":70,\"keyword\":\"엔젤\"},{\"score\":\"0.36197919626240693\",\"rank\":71,\"keyword\":\"토론\"},{\"score\":\"0.3614841967252155\",\"rank\":72,\"keyword\":\"중소기업\"},{\"score\":\"0.3598748506623036\",\"rank\":73,\"keyword\":\"경영자\"},{\"score\":\"0.3596293885110636\",\"rank\":74,\"keyword\":\"수렴\"},{\"score\":\"0.3579593159215859\",\"rank\":75,\"keyword\":\"커뮤니케이션\"},{\"score\":\"0.3565319496294674\",\"rank\":76,\"keyword\":\"분류\"},{\"score\":\"0.3519140396762986\",\"rank\":77,\"keyword\":\"집합\"},{\"score\":\"0.35165514096780454\",\"rank\":78,\"keyword\":\"상담\"},{\"score\":\"0.34869262178291327\",\"rank\":79,\"keyword\":\"자발\"},{\"score\":\"0.34845880778586913\",\"rank\":80,\"keyword\":\"방법\"},{\"score\":\"0.34811330520900335\",\"rank\":81,\"keyword\":\"지성\"},{\"score\":\"0.34801329530425335\",\"rank\":82,\"keyword\":\"정보\"},{\"score\":\"0.34267253325570163\",\"rank\":83,\"keyword\":\"적시\"},{\"score\":\"0.3417254283038995\",\"rank\":84,\"keyword\":\"실패\"},{\"score\":\"0.3406601447172202\",\"rank\":85,\"keyword\":\"엔트로피\"},{\"score\":\"0.3404136388277993\",\"rank\":86,\"keyword\":\"콜센터\"},{\"score\":\"0.34009310426845996\",\"rank\":87,\"keyword\":\"이해관계\"},{\"score\":\"0.3390162366557443\",\"rank\":88,\"keyword\":\"아이디어\"},{\"score\":\"0.3384339432158312\",\"rank\":89,\"keyword\":\"직장인\"},{\"score\":\"0.3370123184288662\",\"rank\":90,\"keyword\":\"원리\"},{\"score\":\"0.33584830465510324\",\"rank\":91,\"keyword\":\"불만\"},{\"score\":\"0.33555967564277117\",\"rank\":92,\"keyword\":\"수립\"},{\"score\":\"0.33551276745468184\",\"rank\":93,\"keyword\":\"투자\"},{\"score\":\"0.3349836971774981\",\"rank\":94,\"keyword\":\"개성\"},{\"score\":\"0.3345931643078367\",\"rank\":95,\"keyword\":\"프레이밍\"},{\"score\":\"0.3336393097350318\",\"rank\":96,\"keyword\":\"수요자\"},{\"score\":\"0.33109604557922256\",\"rank\":97,\"keyword\":\"고급\"},{\"score\":\"0.3295505795421686\",\"rank\":98,\"keyword\":\"도착\"},{\"score\":\"0.3294994372257989\",\"rank\":99,\"keyword\":\"포기\"}]",
//			};
			
			for(int i=0;i<3;i++){
				Process process = new ProcessBuilder("java", "-jar",jarPath+"/word2vec_0.5.jar",inputWord,dataPath+groupName+"_"+docType+"_"+years[i]+".txt").start();	
				
				BufferedReader stdOut = new BufferedReader( new InputStreamReader(process.getInputStream()) );
				String line = "";
				line = stdOut.readLine();
				//line = texts[i];

				JSONArray jsonArray = (JSONArray) JSONValue.parse(line);
				
				ArrayList<String> newArrayList = new ArrayList<String>();
				
				for(int k=0;k<TOPK;k++)
				{
					JSONObject jsonobj = (JSONObject)jsonArray.get(k);
					newArrayList.add(jsonobj.get("keyword").toString());
					
					//newArrayList.add(jsonobj.get("score").toString());
				}
				lists.add(newArrayList);

				
			}
			//Find3
			ArrayList<String> list_ThreeGroups= new ArrayList<String>();
			for(int i=0;i<TOPK;i++)
			{
				int indexofSame1 = lists.get(1).indexOf(lists.get(0).get(i));
				int indexofSame2 = lists.get(2).indexOf(lists.get(0).get(i));
				if(indexofSame1>=0 && indexofSame2>=0)
				{
					list_ThreeGroups.add(lists.get(0).get(i));
				}
			}
			//Find2
			ArrayList<String> list_Two01=new ArrayList<String>();
			ArrayList<String> list_Two02= new ArrayList<String>();
			ArrayList<String> list_Two12=new ArrayList<String>();
			for(int i=0;i<TOPK;i++)
			{
				int indexofSame1 = lists.get(1).indexOf(lists.get(0).get(i));
				int indexofSame2 = lists.get(2).indexOf(lists.get(0).get(i));
				if(indexofSame1>=0 && indexofSame2<0)
				{
					list_Two01.add((lists.get(0).get(i)));
				}
			}
			for(int i=0;i<TOPK;i++)
			{
				int indexofSame1 = lists.get(1).indexOf(lists.get(0).get(i));
				int indexofSame2 = lists.get(2).indexOf(lists.get(0).get(i));
				if(indexofSame2>=0 && indexofSame1<0)
				{
					list_Two02.add((lists.get(0).get(i)));
				}
			}
			for(int i=0;i<TOPK;i++)
			{
				int indexofSame1 = lists.get(0).indexOf(lists.get(1).get(i));
				int indexofSame2 = lists.get(2).indexOf(lists.get(1).get(i));
				if(indexofSame1<0 && indexofSame2>=0)
				{
					list_Two12.add((lists.get(1).get(i)));
				}
			}
			
			////////// delete 과정 ////////////
			
			for(int i=0; i<3; i++) {
				lists.get(i).removeAll(list_ThreeGroups);
				lists.get(i).removeAll(list_Two01);
				lists.get(i).removeAll(list_Two02);
				lists.get(i).removeAll(list_Two12);

			}

			
			ArrayList<String> new_list_0 = new ArrayList<String>();
			ArrayList<String> new_list_1 = new ArrayList<String>();
			ArrayList<String> new_list_2 = new ArrayList<String>();
			ArrayList<ArrayList<String>> new_list_all = new ArrayList<ArrayList<String>>();
			int kk=0;
			
			int threeCnt = 0;
			int twoCnt = 0;
			for(int i=0;i<list_ThreeGroups.size();i++){
				if( kk > 10) break;
				new_list_0.add("<font color=red>"+list_ThreeGroups.get(i)+"</font>");
				new_list_1.add("<font color=red>"+list_ThreeGroups.get(i)+"</font>");
				new_list_2.add("<font color=red>"+list_ThreeGroups.get(i)+"</font>");
	//			System.out.println(list_ThreeGroups.get(i)+"\t"+list_ThreeGroups.get(i)+"\t"+list_ThreeGroups.get(i));
				kk++;
				threeCnt++;
			}
			for(int i=0;i<list_Two01.size();i++){
				if( kk > 10) break;
				new_list_0.add("<font color=blue>"+list_Two01.get(i)+"</font>");
				new_list_1.add("<font color=blue>"+list_Two01.get(i)+"</font>");
				new_list_2.add("　");
		//		System.out.println(list_Two01.get(i)+"\t"+list_Two01.get(i)+"\t"+"   ");
				kk++;
				twoCnt++;
			}
			for(int i=0;i<list_Two02.size();i++){
				if( kk > 10) break;
				new_list_0.add("<font color=blue>"+list_Two02.get(i)+"</font>");
				new_list_1.add("　");
				new_list_2.add("<font color=blue>"+list_Two02.get(i)+"</font>");
		//		System.out.println(list_Two02.get(i)+"\t"+"    "+"\t"+list_Two02.get(i));
				kk++;
				twoCnt++;
			}
			for(int i=0;i<list_Two12.size();i++){
				if( kk > 10) break;
				new_list_0.add("　");
				new_list_1.add("<font color=blue>"+list_Two12.get(i)+"</font>");
				new_list_2.add("<font color=blue>"+list_Two12.get(i)+"</font>");
	//			System.out.println("     "+"\t"+list_Two12.get(i)+"\t"+list_Two12.get(i));
				kk++;
				twoCnt++;
			}
			for(int i=0;i<TOPK-kk;i++)
			{
				new_list_0.add(lists.get(0).get(i));
				new_list_1.add(lists.get(1).get(i));
				new_list_2.add(lists.get(2).get(i));
	//			System.out.println(lists.get(0).get(i) + "\t" + lists.get(1).get(i)+ "\t" + lists.get(2).get(i));
				
			}
			
			new_list_all.add(new_list_0); 
			new_list_all.add(new_list_1);
			new_list_all.add(new_list_2);
	//		System.out.println(new_list_all);
			
			
			JSONArray retArray = new JSONArray();
			if (threeCnt>0){
				JSONArray cluster = new JSONArray();
				for(int i=0;i<threeCnt;i++)
				{
					JSONObject row= new JSONObject();
					row.put("id",3);
					JSONArray arr = new JSONArray();
					arr.add(new_list_all.get(0).get(i));
					arr.add(new_list_all.get(1).get(i));
					arr.add(new_list_all.get(2).get(i));
					row.put("keywords", arr);
					cluster.add(row);
				}
				retArray.add(cluster);
			}	
			if(twoCnt >0)
			{
				JSONArray cluster = new JSONArray();
				for(int i=threeCnt;i<threeCnt+twoCnt;i++)
				{
					JSONObject row= new JSONObject();
					row.put("id",2);
					JSONArray arr = new JSONArray();
					arr.add(new_list_all.get(0).get(i));
					arr.add(new_list_all.get(1).get(i));
					arr.add(new_list_all.get(2).get(i));
					row.put("keywords", arr);
					cluster.add(row);
				}
				retArray.add(cluster);
			}
			if(threeCnt+twoCnt <10)
			{
				JSONArray cluster = new JSONArray();
				for(int i=threeCnt+twoCnt;i<TOPK;i++)
				{
					JSONObject row= new JSONObject();
					row.put("id",1);
					JSONArray arr = new JSONArray();
					arr.add(new_list_all.get(0).get(i));
					arr.add(new_list_all.get(1).get(i));
					arr.add(new_list_all.get(2).get(i));
					row.put("keywords", arr);
					cluster.add(row);
				}
				retArray.add(cluster);
			}
			
			

			/*
			JSONArray json = new JSONArray();
			for(int i=0; i<3; i++) {
				JSONArray json_tmp = new JSONArray();
				for(int j=0; j<TOPK; j++) {
					JSONObject jobj = new JSONObject();
					jobj.put("keyword", new_list_all.get(i).get(j));
					jobj.put("rank", j);
					json_tmp.add(jobj);
				}
				json.add(json_tmp);
			}
			*/
			System.out.println(retArray);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
