package good.luck.hunting.job;

public class ResourceRepo {
	
	public Resource BYR_ZPXX;
	public Resource BYR_BYS;
	public Resource NS_XZ;
	public Resource NS_SZ;
	public Resource NS_LT;
	
	ResourceRepo() {
		init();
	}
	private void init() {
		BYR_ZPXX = new Resource();
		BYR_ZPXX.setBoardUrl("http://bbs.byr.cn/board/JobInfo/mode/6");
		BYR_ZPXX.setBoardName("北邮人_招聘信息专版");
		BYR_ZPXX.setRelPath("/article/JobInfo/");
		BYR_ZPXX.setDBTablel("byrzpxx");
		
		BYR_BYS = new Resource();
		BYR_BYS.setBoardUrl("http://bbs.byr.cn/board/Job/mode/6");
		BYR_BYS.setBoardName("北邮人_毕业生找工作");
		BYR_BYS.setRelPath("/article/Job/");
		BYR_BYS.setDBTablel("byrbys");
		
		NS_XZ = new Resource();
		NS_XZ.setBoardUrl("http://www.newsmth.net/nForum/board/Career_Campus");
		NS_XZ.setBoardName("水木社区_校园招聘信息");
		NS_XZ.setRelPath("/nForum/article/Career_Campus/");
		NS_XZ.setDBTablel("nsxz");
		
		NS_SZ = new Resource();
		NS_SZ.setBoardUrl("http://www.newsmth.net/nForum/board/Career_Upgrade");
		NS_SZ.setBoardName("水木社区_社会招聘");
		NS_SZ.setRelPath("/nForum/article/Career_Upgrade/");
		NS_SZ.setDBTablel("nssz");
		
		NS_LT = new Resource();
		NS_LT.setBoardUrl("http://www.newsmth.net/nForum/board/ExecutiveSearch");
		NS_LT.setBoardName("水木社区_猎头招聘");
		NS_LT.setRelPath("/nForum/article/ExecutiveSearch/");
		NS_LT.setDBTablel("nslt");
	}
}
