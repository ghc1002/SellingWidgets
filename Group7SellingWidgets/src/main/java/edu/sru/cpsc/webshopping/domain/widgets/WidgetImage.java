package edu.sru.cpsc.webshopping.domain.widgets;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import edu.sru.cpsc.webshopping.domain.market.MarketListing;
import edu.sru.cpsc.webshopping.domain.market.Transaction;


@Entity
public class WidgetImage {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "image_id")
	private Long image_id;
	
	private String imageName;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	private MarketListing marketListingId;

	public Long getImage_id() {
		return image_id;
	}

	public void setImage_id(Long image_id) {
		this.image_id = image_id;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public MarketListing getMarketListingId() {
		return marketListingId;
	}

	public void setMarketListingId(MarketListing marketListingId) {
		this.marketListingId = marketListingId;
	}


}
