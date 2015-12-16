package Collects;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class NDSL {
	/**
	 * @param args
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 */

	public static void main(String[] args) throws SAXException, IOException,ParserConfigurationException {
		LinkedList<String> Keyword = new LinkedList<String>();
		String path = args[1];
		
		String doc_type = "";
		if (args[0].equals("보건") || args[0].equals("health")) {
			doc_type = "healthcare";
			Keyword.add("노인");
			Keyword.add("농촌");
			Keyword.add("간호직");
			Keyword.add("공공");
			Keyword.add("정신");
			Keyword.add("환경");
			Keyword.add("구강");
			Keyword.add("안전");
			Keyword.add("의료법");
			Keyword.add("재가");
			Keyword.add("학교");
			Keyword.add("의료개혁");
			Keyword.add("의료기술");
			Keyword.add("진료소");
			Keyword.add("의사");
			Keyword.add("산업안전");
			Keyword.add("간호사");
			Keyword.add("의료정보");
			Keyword.add("의료정책");
			Keyword.add("복지");
		}
		
		if(args[0].equals("반도체") || args[0].equals("semi")) {
		doc_type = "semiconductor";
		 Keyword.add("산화물"); Keyword.add("표면실장"); Keyword.add("광 증폭기");
		 Keyword.add("전력증폭기"); Keyword.add("p형"); Keyword.add("레이저");
		 Keyword.add("화합물"); Keyword.add("소자"); Keyword.add("검출기");
		 Keyword.add("촉매");
		 Keyword.add("메모리"); Keyword.add("고분자"); Keyword.add("나노구조");
		 Keyword.add("습식 세정"); Keyword.add("자성"); Keyword.add("센서");
		 Keyword.add("챔버"); Keyword.add("칠러"); Keyword.add("웨이퍼");
		 Keyword.add("초격자구조");
		}
		
		if(args[0].equals("나노") || args[0].equals("nano")) {
			doc_type = "nano";
			Keyword.add("Ag");
			Keyword.add("튜브");
			Keyword.add("간극");
			Keyword.add("가공");
			Keyword.add("띠");
			Keyword.add("구조체");
			Keyword.add("잉크");
			Keyword.add("탐침");
			Keyword.add("임프린트");
			Keyword.add("섬유");
			Keyword.add("카본");
			Keyword.add("센서");
			Keyword.add("유체");
			Keyword.add("분말");
			Keyword.add("슬래그");
			Keyword.add("파이버");
			Keyword.add("코팅");
			Keyword.add("촉매");
			Keyword.add("충전제");
			Keyword.add("패턴");
		}
		




		for(int k=0; k<3; k++) {
			FileWriter fw = null;

			if(k==0) {
				fw = new FileWriter(path+doc_type+"_paper");
			//	fw = new FileWriter("D:\\2015_12_kisti\\"+args[0]+"_논문.txt");
			}
			if(k==1) {
				fw = new FileWriter(path+doc_type+"_patent");
			//	fw = new FileWriter("D:\\2015_12_kisti\\"+args[0]+"_특허.txt");

			}
			if(k==2) {
				fw = new FileWriter(path+doc_type+"_report");
			//	fw = new FileWriter("D:\\2015_12_kisti\\"+args[0]+"_보고서.txt");
			}
			String doc_Info = "";
			Iterator<String> it_key = Keyword.iterator();

			while (it_key.hasNext()) {
				try{
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = factory.newDocumentBuilder();
					Document doc = null;
					int p = 1;
					int q = 1;
					int total_count = 0;
					String query = it_key.next();
					if(k==0) { //논문
						doc = builder.parse("http://openapi.ndsl.kr/itemsearch.do?keyValue=00000000&target=JAKO&searchField=BI&displayCount=100&startPosition="+ p+ "&returnType=xml&responseGroup=simple&query="+ query);
						doc_Info = "articleInfo";
						
					}
					if(k==1) { //특허
						doc = builder.parse("http://openapi.ndsl.kr/itemsearch.do?keyValue=00000000&target=KPAT&searchField=BI&displayCount=100&startPosition="+p+"&returnType=xml&query="+query);
						//특허
						doc_Info = "patentInfo";
					}
					if(k==2) {
						doc = builder.parse("http://openapi.ndsl.kr/itemsearch.do?keyValue=00000000&target=TRKO&searchField=BI&displayCount=100&startPosition="+p+"&returnType=xml&query="+query);
						//보고서
						doc_Info="reportInfo";
					}





				doc.getDocumentElement().normalize();
				Element root = doc.getDocumentElement(); // 루트 엘리먼트 접근
				//
				Node resultNode = root.getElementsByTagName("resultSummary").item(0);

				Node countNode = ((Element) resultNode).getElementsByTagName("totalCount").item(0);
				// System.out.println( ((Element)countNode).getTagName());

				total_count = Integer.parseInt(countNode.getTextContent());

				
				for (int j = 1; j < total_count; j += 100) {
					if(k==0) {
						doc = builder.parse("http://openapi.ndsl.kr/itemsearch.do?keyValue=00000000&target=JAKO&searchField=BI&displayCount=100&startPosition="+ j+ "&returnType=xml&responseGroup=simple&query="+ query);
						// 논문
					}
					if(k==1) {
						doc = builder.parse("http://openapi.ndsl.kr/itemsearch.do?keyValue=00000000&target=KPAT&searchField=BI&displayCount=100&startPosition="+j+"&returnType=xml&query="+query);
						// 특허
					}
					if(k==2) {
						doc = builder.parse("http://openapi.ndsl.kr/itemsearch.do?keyValue=00000000&target=TRKO&searchField=BI&displayCount=100&startPosition="+j+"&returnType=xml&query="+query);
						// 보고서
					}
					doc.getDocumentElement().normalize();
					// p=j*100+1;

					//NodeList list2 = doc.getElementsByTagName("articleInfo");
					 //NodeList list2 = doc.getElementsByTagName("reportInfo");
				//	NodeList list2 = doc.getElementsByTagName("patentInfo");
					NodeList list2 = doc.getElementsByTagName(doc_Info);
					boolean flag = true;
					for (int i = 0; i < list2.getLength(); i++) {
						Node id = list2.item(i);
						if (id.getNodeType() == Node.ELEMENT_NODE) {
							Element idElmnt = (Element) id;
							if(idElmnt.getAttribute("kistiID").contains("JAKO") || idElmnt.getAttribute("kistiID").contains("TRKO") || idElmnt.getAttribute("kistiID").contains("KOR") ) {
								 fw.write("kisitID\n");
								 fw.write(idElmnt.getAttribute("kistiID")+"\n");
								flag = true;
							}
							else {
								flag = false;
							}

						}

						for (Node node = list2.item(i).getFirstChild(); node != null; node = node.getNextSibling()) {
							String Title = node.getNodeName();
							if(flag == true) {
								if (Title.equals("articleTitleInfo") || Title.equals("reportTitleInfo")) {
									fw.write("Title"+q);
									fw.write(node.getTextContent());
									// 위에 2줄 논문 + 보고서
									
									q++;
								}

								 if (Title.equals("patentTitle")){
									 fw.write("Title"+q+"\n");
									 fw.write(node.getTextContent()+"\n");
		
								 // 위에 2줄 특허

								q++;
							}
								 String Abstract = node.getNodeName();
							 if (Abstract.equals("abstract")) {
								 fw.write("Abstract\n"+node.getTextContent()+"\n");
									 // 위에 특허
							 }
	
							 if (Abstract.equals("abstractInfo")) {
								 fw.write("Abstract" + node.getTextContent());
								// 위에 논문 + 보고서에 해당

								 // 위에 논문 + 보고서
							 }
							}
							else {
								break;
							}

							// fw.write("Abstract\n"+node.getTextContent()+"\n");

						}
					}
					
						
					}


			//System.out.println(q);
				// pw.close();
				} catch(Exception e) {
					
				}
			} // while
			System.out.println(args[0]+" finish");
			fw.close();
		}
		
		// fw.close();
	}
	} // main
