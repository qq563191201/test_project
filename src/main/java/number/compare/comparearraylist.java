package number.compare;

public class comparearraylist {
	public static void main(String args[]){
		Long l1 = System.currentTimeMillis();
		Double temp = 26d;
		Double[] arr = {10d,15d,25d,35d,45d};
		Double max = 0.0;
		Double min = 0.0;
		for (int i=0;i<arr.length;i++) {
			max = arr[i];
			min = arr[i];
			int z = 0;
			for(int j=0;j<arr.length;j++){
				if(max<arr[j]){
					max = arr[j];
				}
				if(min>arr[j]){
					min = arr[j];
				}
				if(arr[i]<arr[j]){
					if(z == 0){
						if(temp>arr[i] && temp<arr[j]){
							if(temp>min && (temp<max || temp==max)){
								System.out.println("绿");
							}
							System.out.println(arr[i]+">>>"+arr[j]);
						}
					}
					z++;
				}
			}
		}
		if(temp<min || temp == min){
			System.out.println("红min");
		}
		if(temp>max){
			System.out.println("红max");
		}
		Long l2 = System.currentTimeMillis();
		System.out.println(max+"/"+min+">>>"+(l2-l1)+"ms");
	}
}
