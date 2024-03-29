package main;

import java.awt.*;

/**
 * Square consists of up to one character, up to four walls (bools) and up to
 * one estate - Mathias
 */
public class Square {
	private Character character;
	private int x;
	private int y;
	private Estate estate;
	private Weapon weapon;
	private boolean blocked = false;

	public Square(int x, int y) {
		this.x = x;
		this.y = y;
		estate = null;
		weapon = null;
	}

	/**
	 * Gets the weapon for this square
	 */
	public Weapon getWeapon() {
		return weapon;
	}

	/**
	 * Sets whether the square has a weapon
	 */
	public void setWeapon(Weapon w) {
		weapon = w;
	}

	/**
	 * Sets whether the square has a state
	 *
	 * @param e
	 */
	public void setEstate(Estate e) {
		estate = e;
	}

	/**
	 * Sets whether the square can be moved onto by a player.
	 *
	 * @param b
	 */
	public void setBlocked(Boolean b) {
		blocked = b;
	}

	/**
	 * Returns whether the square is accessible by a player
	 *
	 * @return boolean blocked
	 */
	public boolean isBlocked() {
		return blocked;
	}

	/**
	 * Adds a character to a square Used for drawing the characters initials on the
	 * board
	 *
	 * @param c
	 */
	public void addCharacter(Character c) {
		character = c;
	}

	/**
	 * Removes a character from a square Used for removing a players intial from a
	 * square
	 *
	 * @return
	 */
	public Character removeCharacter() {
		Character toRemove = character;
		character = null;
		return toRemove;
	}

	/**
	 * Returns the character on the square
	 *
	 * @return the character on the current square
	 */
	public Character getCharacter() {
		return character;
	}

	/**
	 * Returns the column of the square
	 *
	 * @return x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Returns the row of the square
	 *
	 * @return y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Draws the characters, estates, doors, or blocked areas squares on the screen
	 * as a text display
	 */
	public Color draw() {

		boolean wall = blocked && isEstateBorder();
		boolean unreachableSquare = blocked && estate == null;
		boolean door = isEstateBorder() && estate !=null;
		if (character != null) {
			return character.getColour();
		} else {
			if (wall) {
				return new Color(74, 50, 34); // dark brown
			} else if (unreachableSquare) {
				return new Color(85, 85, 85);
			} else if (door) {
				return HobbyDetectives.BROWN;
			} else {
				if (estate != null) {
//					if (isEstateTitle()) {
//						return HobbyDetectives.CYAN_BOLD;
//					} else {
//						System.out.print(" ");
//					}
					return new Color(48, 69, 41);
				} else {
					return new Color(209, 192, 168); // beige background
				}
			}
		}
	}



	/**
	 * Figures out where the estate title should be drawn within an estate Consists
	 * of two initials
	 *
	 * @return whether the square is a title square or not
	 */
	private boolean isEstateTitle() {
		int estateCornerX = estate.getX() + estate.width - 2;
		int estateCornerY = estate.getY() + estate.height - 2;
		int squareX = this.getX();
		int squareY = this.getY();
		if ((squareX == estateCornerX && squareY == estateCornerY)
				|| (squareX == estateCornerX - 1 && squareY == estateCornerY)) {
			return true;
		}
		return false;
	}

	/**
	 * Figures out if the current square is on the border of an estate for drawing.
	 *
	 * @return whether the square is a perimeter estate square
	 */

	private boolean isEstateBorder() {
		// Check if the square is on the perimeter of the estate
		if (estate != null) {
			int squareX = this.getX();
			int squareY = this.getY();
			int estateX = estate.getX();
			int estateY = estate.getY();
			int estateWidth = estate.width;
			int estateHeight = estate.height;

			// Check if the square is on the perimeter of the estate
			if ((squareX == estateX || squareX == estateX + estateWidth - 1)
					|| (squareY == estateY || squareY == estateY + estateHeight - 1)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the estate of the square
	 *
	 * @return estate
	 */

	public Estate getEstate() {
		return estate;
	}

	public String toString(){
		String result = "Square Information\n" + "X: " + (x+1) + " Y: " + (y+1);
		if (estate != null){ result += "\nEstate: " + estate; }
		if (character != null){ result += "\nCharacter: " + character; }
		if (weapon != null){ result += "\nWeapon: " + weapon.getName(); }
		return result;
	}
}
