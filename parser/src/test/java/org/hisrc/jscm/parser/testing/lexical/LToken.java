package org.hisrc.jscm.parser.testing.lexical;

public class LToken {

	private final String id;
	private final int kind;
	private final String image;

	public LToken(String id, int kind, String image) {
		this.id = id;
		this.kind = kind;
		this.image = image;
	}

	public String getId() {
		return id;
	}

	public int getKind() {
		return kind;
	}

	public String getImage() {
		return image;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + kind;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LToken other = (LToken) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (kind != other.kind)
			return false;
		return true;
	}
	
	public String getSignature() {
		return "Token [id=" + id + ", image=" + image + "]";
	}

	@Override
	public String toString() {
		return "Token [id=" + id + ", kind=" + kind + ", image=" + image + "]";
	}

}
