package model.queries;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public abstract class QueryElement {
	private List<QueryElement> queryComposition;
}
