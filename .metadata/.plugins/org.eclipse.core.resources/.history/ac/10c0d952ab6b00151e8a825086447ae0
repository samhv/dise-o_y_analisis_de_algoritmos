package algoritmos;

import java.time.Duration;
import java.time.Instant;

public abstract class TextSearcher {
	
	protected int numberOfComparations;
	protected long timeOfExecutionNano;
	protected long timeOfExecutionMill;
	protected Duration timeOfExecutionDate;
	
	public TextSearcher(){
		this.initParams();
	}
	
	public int getNumberOFComparations(){
		return this.numberOfComparations;
	}
	
	public long getTimeOfExecutionInNano(){
		return this.timeOfExecutionNano;
	}
	
	public long getTimeOfExecutionInMill(){
		return this.timeOfExecutionMill;
	}

	public Duration getTimeOfExecutionInDate(){
		return this.timeOfExecutionDate;
	}
	
	public int doSearch(String pattern, String text){
		this.initParams();
		int numberOfMatchs;
		long timeBeforeNano;
		long timeBeforeMill;
		Instant timeBeforeDate;
		long timeAfterNano;
		long timeAfterMill;
		Instant timeAfterDate;
		
		timeBeforeDate = Instant.now();
		timeBeforeMill = System.currentTimeMillis();
		timeBeforeNano = System.nanoTime();
		
		numberOfMatchs = this.search(pattern,text);
		
		timeAfterNano = System.nanoTime();
		timeAfterMill = System.currentTimeMillis();
		timeAfterDate = Instant.now();
				
		this.timeOfExecutionMill = timeAfterMill - timeBeforeMill;
		this.timeOfExecutionNano = timeAfterNano - timeBeforeNano;
		this.timeOfExecutionDate = Duration.between(timeBeforeDate, timeAfterDate);
		
		return numberOfMatchs;
	}
	
	protected abstract int search(String pattern, String text);
	
	private void initParams(){
		this.numberOfComparations=0;
	}
}
