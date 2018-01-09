package transformers;

import java.util.concurrent.CompletionStage;

public interface ICompletionStageTransformer<T,U> {

	public CompletionStage<U> transform(T arg) throws TransformNotPossibleException;
	
	public CompletionStage<T> reverseTransform(U arg) throws TransformNotPossibleException; 
	
}
