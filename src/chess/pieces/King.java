package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {
	
	private ChessMatch chessMatch;

	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}
	
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}
	
	private boolean testRookCastling(Position position) {
		ChessPiece p = ((ChessPiece)getBoard().piece(position));
		return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0 && getMoveCount() == 0;
	}
	
	@Override
	public String toString() {
		return "K";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0,0);

		//acima
		p.setValue(position.getRow()-1, position.getColumn());
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//abaixo
		p.setValue(position.getRow()+1, position.getColumn());
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//esquerda
		p.setValue(position.getRow(), position.getColumn()-1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//direita
		p.setValue(position.getRow(), position.getColumn()+1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//no
		p.setValue(position.getRow()-1, position.getColumn()-1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//ne
		p.setValue(position.getRow()-1, position.getColumn()+1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//so
		p.setValue(position.getRow()+1, position.getColumn()-1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//se
		p.setValue(position.getRow()+1, position.getColumn()+1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//#specialMove castling
		if (getMoveCount() == 0 && !chessMatch.getCheck()) {
			
			if (getColor() == Color.WHITE) {
				// #specialMove castling kingside rook white
				Position posR1W = new Position(position.getRow(), position.getColumn() + 3);
				if (testRookCastling(posR1W)) {
					Position p1 = new Position(position.getRow(), position.getColumn() + 1);
					Position p2 = new Position(position.getRow(), position.getColumn() + 2);
					if (getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
						mat[position.getRow()][position.getColumn() + 2] = true;
					}
				}
				
				// #specialMove castling queenside rook white
				Position posR2W = new Position(position.getRow(), position.getColumn() - 4);
				if (testRookCastling(posR2W)) {
					Position p1 = new Position(position.getRow(), position.getColumn() - 1);
					Position p2 = new Position(position.getRow(), position.getColumn() - 2);
					Position p3 = new Position(position.getRow(), position.getColumn() - 3);
					if (getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) {
						mat[position.getRow()][position.getColumn() - 2] = true;
					}
				}
			} else {			
				// #specialMove castling queenside rook black
				Position posR1B = new Position(position.getRow(), position.getColumn() + 4);
				if (testRookCastling(posR1B)) {
					Position p1 = new Position(position.getRow(), position.getColumn() + 1);
					Position p2 = new Position(position.getRow(), position.getColumn() + 2);
					Position p3 = new Position(position.getRow(), position.getColumn() + 3);
					if (getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) {
						mat[position.getRow()][position.getColumn() + 2] = true;
					}
				}
				
				// #specialMove castling kingside rook black
				Position posR2B = new Position(position.getRow(), position.getColumn() - 3);
				if (testRookCastling(posR2B)) {
					Position p1 = new Position(position.getRow(), position.getColumn() - 1);
					Position p2 = new Position(position.getRow(), position.getColumn() - 2);
					if (getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
						mat[position.getRow()][position.getColumn() - 2] = true;
					}
				}
			}
		}
		
		return mat;
	}

}
