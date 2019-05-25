
package forms;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import domain.CategoryPrice;

public class PurchaseAttendeeForm {

	private Integer			num;

	private CategoryPrice	category;


	@NotNull
	@Min(1)
	@Max(10)
	public Integer getNum() {
		return this.num;
	}

	public void setNum(final Integer num) {
		this.num = num;
	}

	@NotNull
	public CategoryPrice getCategory() {
		return this.category;
	}

	public void setCategory(final CategoryPrice category) {
		this.category = category;
	}

}
