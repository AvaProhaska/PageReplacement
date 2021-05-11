package homework3;
import java.io.IOException;
import java.util.Scanner;

public class PageReplacement
{
	// define variables 
	static Scanner scan=new Scanner(System.in);
	double num_pages;
	double[] page;
	int num_frames;
	double frames[];
	int faults;
	int count;
	double rate;
	Integer fifoFaultRate;
	Integer lruFaultRate;
	float faultRate;


	// define constructor
	public PageReplacement() throws IOException
	{
		// Read Number of Frames
		System.out.println("Enter Number of Frames (values 2-5) :");
		num_frames=scan.nextInt();
		frames=new double[num_frames];
		count=1;

		// Read Number of Pages
		System.out.println("Enter Number of Pages: ");
		num_pages=scan.nextInt();
		page=new double[(int) num_pages];

	}
	
	void get_Reference() throws IOException
	{
		int i;
		System.out.println("The Reference String is :");
		for(i=0;i<num_pages;i++)
		{
			page[i]=0 + (int) scan.nextInt();
		}

		// Printing the String
		for(i=0;i<num_pages;i++)
		{
			System.out.print(page[i]);
		}
		System.out.println();
		for(i=0;i<num_frames;i++)
			frames[i]=-1;
	}
	
	// Function to Reset the Frame Array
	void Frame_reset()
	{
		int j;
		for(j=0;j<num_frames;j++)
			frames[j]=0;
		faults=0;
		count=1;
	}
	// FIFO Replacement
	void FIFO()
	{
		int i,j,k=0;
		// Reset the frame set
		Frame_reset();
		boolean flag=false;
		
		for(i=0;i<num_pages;i++)
		{
			for(j=0;j<num_frames;j++)
			{
				if(page[i]==frames[j])
					flag=true;

			}
			if(flag==false)
			{
				frames[k]=page[i];

				if(k==num_frames-1)
					k=0;
				else
					k++;
				faults++;
			}
			Display();
			flag=false;
		}
		fifoFaultRate = faults;
		faultRate = (float) (faults/num_pages);
		System.out.println("The analysis of FIFO Page Replacement is:");
		System.out.println("Number of Page Faults = "+faults);
		System.out.println("Fault Rate = "+ faultRate);
		
		System.out.println("\n");

	}
	// LRU Replacement
	void LRU()
	{
		int i,j,showFames[],max;
		Frame_reset();
		showFames=new int[num_frames];
		boolean found=false;
		for(i=0;i<num_pages;i++)
		{
			for(j=0;j<num_frames;j++)
				showFames[j]++;
			for(j=0;j<num_frames;j++)
			{
				if(page[i]==frames[j])
				{
					found=true;
					showFames[j]=0;
				}
			}
			if(found==false)
			{
				max=0;
				for(j=0;j<num_frames;j++)
				{
					if(showFames[j]>showFames[max])
						max=j;
				}
				frames[max]=page[i];
				showFames[max]=0;
				faults++;
			}
			Display();
			found=false;
		}
		lruFaultRate = faults;
		faultRate = (float) (faults/num_pages);
		System.out.println("The analysis of LRU Page Replacement is:");
		System.out.println("Number of Page Faults = "+faults);
		System.out.println("Fault Rate = "+faultRate);

	}

	// Display Function
	void Display()
	{
		int i;
		System.out.print("Page Frame "+count+" :");
		for(i=0;i<num_frames;i++)
		{
			if(frames[i]==-1)
				System.out.print(" -");
			else
				System.out.print(" "+frames[i]);
		}
		System.out.print("\n");
		count++;
	}


	//Main method to take in user input 
	public static void main(String[] args) throws IOException{

		String output;
		PageReplacement p=new PageReplacement();
		p.get_Reference();
		do
		{
			p.FIFO();
			p.LRU();

			System.out.println("\n");

		 	if(p.fifoFaultRate < p.lruFaultRate) {
				System.out.println("The better policy is FIFO");	
			}
		 	
		if(p.fifoFaultRate > p.lruFaultRate) {
				System.out.println("The better policy is LRU");	
		}
			if(p.fifoFaultRate == (p.lruFaultRate)) {
				System.out.println("Either policy is applicable");
			}

			System.out.println("Enter Y to continue");
			scan.nextLine();
			output=scan.nextLine();
		}
		while(output.compareToIgnoreCase("y")==0);
	}
}

