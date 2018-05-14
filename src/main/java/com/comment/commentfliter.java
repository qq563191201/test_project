package com.comment;

import java.util.Set;

public class commentfliter {


	public static void main(String[] args) {
		filter();
    }
	
	public static void word(){
		String words = "手淫，叫床，绳虐，精液，淫欲，情欲，色欲，爱液，高潮，肛交，淫穴，阴水，阴茎，阴蒂，阴道，阴唇，射精，鸡巴，色情，烂逼，卖逼，贱逼，性虐待，兽交，鸡奸，诱奸，性奴，暴乳，淫娃，巨乳，生殖器，性欲，乳头，乳房，阳具，操逼，法轮功，李洪志，明慧新闻，胡锦涛，江泽民，毛泽东，邓小平，习近平，共产党，中共，台独，台湾独立，藏独，西藏独立，疆独，新疆独立，天安门屠杀，发抡功，九评，自慰，A片，A级，三级，裸体，胴体，失身，私处，强奸，轮奸，群奸，SM，情色，色图，色诱，一夜情，夜激情，禁片，淫贱，波波，奸淫，肛交，淫荡，裸女，3级片，三级片，四级片，4级片，我操你，B，色情，骚货，日死你，日你，去你妈的，屁眼，卖比，卖逼，烂比，烂逼，狂操，靠你妈，贱人，贱货，狗操，狗b，二逼，二B，大花逼，大逼，操他，操你妈，操你，吮吸，肛，虐待，奸尸，虐猫，虐畜，兽交，鸡奸，淫乱，乱伦，处女膜，荡妇，暴奶，巨乳，性爱，发骚，骚妇，骚妹，AV，女优，妓女，发浪，破处，受孕，淫靡，开苞，芳穴，，蜜液，小鸡鸡，JJ，花蕊，猥亵，做爱，性交，性游戏，性欲，风骚，裸戏，罩杯，美乳，贞操，玉体，波霸，裸体，自摸，丰胸翘臀，赤裸，巨波，双沟，臀沟，乳沟，酥胸，乳晕，双峰，凸点，闷骚，淫妇，双乳，豪乳，胴体，欲火，咪咪，燥热，骚妖，媚妙，波霸，色诱，呻吟，强暴， gay，逼样，婊子，操逼，操比，操蛋，大b，大B，干你娘，干死你，贱比，杂种，招妓，作爱，鸦片，冰毒，吗啡，摇头丸，注射毒品，海洛因，走私，百家乐，廿一点，鱼虾蟹骰子，骰宝赌大，骰宝赌小，富贵三公，三公百家乐，廿五点，联奖扑克，牌九，麻将牌九，雀九，番摊，角子机，白鸽票，泵波拿，博彩，大法，大纪元，法轮，法轮功，法轮大法，人民报，真善忍，转法轮，自焚，一党，多党，民主，专政，民运，六四，暴动，暴乱，发抡，发伦，发伦功，发论，发论公，发论功，法*功，法lun功，法功，法愣，法仑，法伦，法论，法十轮十功，法谪，抡功，伦功，轮大，氵去，氵去车仑工力，人兽，2P，3P，群P，SM，女优，肉缝，肉棒，骚蹄子，消魂夺魄，赤裸，娇喘";
		String[] arr = words.split("，");
		for (String string : arr) {
			System.out.println(string);
		}
	}

	public static void filter(){
		SensitivewordFilter filter = new SensitivewordFilter();
        System.out.println("敏感词的数量：" + filter.sensitiveWordMap.size());
        String string = "太多的伤感情怀也许只局限于饲养基地 荧幕中的情节，主人公尝试着去用某种方式渐渐的很潇洒地释自杀指南怀那些自己经历的伤感。"
                        + "然后法轮功 我们的扮演的角色就是跟随着主人公的喜红客联盟 怒哀乐而过于牵强的把自己的情感也附加于银幕情节中，然后感动就流泪，"
                        + "难过就躺在某一个人的怀里尽情的阐述心扉或者手机卡复制器一个人一杯红酒一部电影在夜三级片 深人静的晚上，关上电话静静的发呆着。";
        System.out.println("待检测语句字数：" + string.length());
        long beginTime = System.currentTimeMillis();
        Set<String> set = filter.getSensitiveWord(string, 1);
        long endTime = System.currentTimeMillis();
        System.out.println("语句中包含敏感词的个数为：" + set.size() + "。包含：" + set);
        System.out.println("总共消耗时间为：" + (endTime - beginTime));
	}

}
