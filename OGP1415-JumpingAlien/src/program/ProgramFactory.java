package program;

import java.util.List;
import java.util.Map;

import jumpingalien.model.*;
import jumpingalien.part3.programs.IProgramFactory;
import jumpingalien.part3.programs.SourceLocation;
import type.*;
import program.*;
import expression.*;
import statement.*;

public class ProgramFactory implements IProgramFactory<Expression<?>, Statement, Type, Program> {

	@Override
	public Expression<?> createReadVariable(String variableName,
			Type variableType, SourceLocation sourceLocation) {
		return new ReadVariable(variableName, variableType);
	}

	@Override
	public Expression<?> createDoubleConstant(double value,
			SourceLocation sourceLocation) {
		return new DoubleConstant(value);
	}

	@Override
	public Expression<?> createTrue(SourceLocation sourceLocation) {
		return new TrueExpression();
	}

	@Override
	public Expression<?> createFalse(SourceLocation sourceLocation) {
		return new FalseExpression();
	}

	@Override
	public Expression<?> createNull(SourceLocation sourceLocation) {
		return new Null();
	}

	@Override
	public Expression<?> createSelf(SourceLocation sourceLocation) {
		return new Self();
	}

	@Override
	public Expression<?> createDirectionConstant(
			jumpingalien.part3.programs.IProgramFactory.Direction value,
			SourceLocation sourceLocation) {
		return new DirectionConstant(value);
	}

	@Override
	public Expression<?> createAddition(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new Addition(left, right);
	}

	@Override
	public Expression<?> createSubtraction(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new Substraction(left, right);
	}

	@Override
	public Expression<?> createMultiplication(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new Multiplication(left, right);
	}

	@Override
	public Expression<?> createDivision(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new Division(left, right);
	}

	@Override
	public Expression<?> createSqrt(Expression expr, SourceLocation sourceLocation) {
		return new Sqrt(expr);
	}

	@Override
	public Expression<?> createRandom(Expression maxValue,
			SourceLocation sourceLocation) {
		return new Random(maxValue);
	}

	@Override
	public Expression<?> createAnd(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new And(left, right);
	}

	@Override
	public Expression<?> createOr(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new Or(left, right);
	}

	@Override
	public Expression<?> createNot(Expression expr, SourceLocation sourceLocation) {
		return new Not(expr);
	}

	@Override
	public Expression<?> createLessThan(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new LessThan(left, right);
	}

	@Override
	public Expression<?> createLessThanOrEqualTo(Expression left,
			Expression right, SourceLocation sourceLocation) {
		return new LessThanOrEquals(left, right);
	}

	@Override
	public Expression<?> createGreaterThan(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new GreaterThan(left, right);
	}

	@Override
	public Expression<?> createGreaterThanOrEqualTo(Expression left,
			Expression right, SourceLocation sourceLocation) {
		return new GreaterThanOrEquals(left, right);
	}

	@Override
	public Expression<?> createEquals(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new Equals(left, right);
	}

	@Override
	public Expression<?> createNotEquals(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new NotEquals(left, right);
	}

	@Override
	public Expression<?> createGetX(Expression expr, SourceLocation sourceLocation) {
		return new GetX(expr);
	}

	@Override
	public Expression<?> createGetY(Expression expr, SourceLocation sourceLocation) {
		return new GetY(expr);
	}

	@Override
	public Expression<?> createGetWidth(Expression expr,
			SourceLocation sourceLocation) {
		return new GetWidth(expr);
	}

	@Override
	public Expression<?> createGetHeight(Expression expr,
			SourceLocation sourceLocation) {
		return new GetHeight(expr);
	}

	@Override
	public Expression<?> createGetHitPoints(Expression expr,
			SourceLocation sourceLocation) {
		return new GetHitpoints(expr);
	}

	@Override
	public Expression<?> createGetTile(Expression x, Expression y,
			SourceLocation sourceLocation) {
		return new GetTile(x, y);
	}

	@Override
	public Expression<?> createSearchObject(Expression direction,
			SourceLocation sourceLocation) {
		return new SearchObject(direction);
	}

	@Override
	public Expression<?> createIsMazub(Expression expr,
			SourceLocation sourceLocation) {
		return new IsMazub(expr);
	}

	@Override
	public Expression<?> createIsShark(Expression expr,
			SourceLocation sourceLocation) {
		return new IsShark(expr);
	}

	@Override
	public Expression<?> createIsSlime(Expression expr,
			SourceLocation sourceLocation) {
		return new IsSlime(expr);
	}

	@Override
	public Expression<?> createIsPlant(Expression expr,
			SourceLocation sourceLocation) {
		return new IsPlant(expr);
	}

	@Override
	public Expression<?> createIsDead(Expression expr,
			SourceLocation sourceLocation) {
		return new IsDead(expr);
	}

	@Override
	public Expression<?> createIsTerrain(Expression expr,
			SourceLocation sourceLocation) {
		return new IsTerrain(expr);
	}

	@Override
	public Expression<?> createIsPassable(Expression expr,
			SourceLocation sourceLocation) {
		return new IsPassable(expr);
	}

	@Override
	public Expression<?> createIsWater(Expression expr,
			SourceLocation sourceLocation) {
		return new IsWater(expr);
	}

	@Override
	public Expression<?> createIsMagma(Expression expr,
			SourceLocation sourceLocation) {
		return new IsMagma(expr);
	}

	@Override
	public Expression<?> createIsAir(Expression expr, SourceLocation sourceLocation) {
		return new IsAir(expr);
	}

	@Override
	public Expression<?> createIsMoving(Expression expr, Expression direction,
			SourceLocation sourceLocation) {
		return new IsMoving(expr);
	}

	@Override
	public Expression<?> createIsDucking(Expression expr,
			SourceLocation sourceLocation) {
		return new IsDucking(expr);
	}

	@Override
	public Expression<?> createIsJumping(Expression expr,
			SourceLocation sourceLocation) {
		return new IsJumping(expr);
	}

	@Override
	public Statement createAssignment(String variableName, Type variableType,
			Expression value, SourceLocation sourceLocation) {
		return new Assignment(variableName, variableType, value);
	}

	@Override
	public Statement createWhile(Expression condition, Statement body,
			SourceLocation sourceLocation) {
		return new While(condition,body);
	}

	@Override
	public Statement createForEach(
			String variableName,
			jumpingalien.part3.programs.IProgramFactory.Kind variableKind,
			Expression where,
			Expression sort,
			jumpingalien.part3.programs.IProgramFactory.SortDirection sortDirection,
			Statement body, SourceLocation sourceLocation) {
		return new ForEach(variableName, variableKind, where, sort, sortDirection, body);
	}

	@Override
	public Statement createBreak(SourceLocation sourceLocation) {
		return new Break();
	}

	@Override
	public Statement createIf(Expression condition, Statement ifBody,
			Statement elseBody, SourceLocation sourceLocation) {
		return new If(condition, ifBody, elseBody);
	}

	@Override
	public Statement createPrint(Expression value, SourceLocation sourceLocation) {
		return new Print(value);
	}

	@Override
	public Statement createStartRun(Expression direction,
			SourceLocation sourceLocation) {
		return new StartRun(direction);
	}

	@Override
	public Statement createStopRun(Expression direction,
			SourceLocation sourceLocation) {
		return new StopRun(direction);
	}

	@Override
	public Statement createStartJump(SourceLocation sourceLocation) {
		return new StartJump();
	}

	@Override
	public Statement createStopJump(SourceLocation sourceLocation) {
		return new StopJump();
	}

	@Override
	public Statement createStartDuck(SourceLocation sourceLocation) {
		return new StartDuck();
	}

	@Override
	public Statement createStopDuck(SourceLocation sourceLocation) {
		return new StopDuck();
	}

	@Override
	public Statement createWait(Expression duration,
			SourceLocation sourceLocation) {
		return new Wait(duration);
	}

	@Override
	public Statement createSkip(SourceLocation sourceLocation) {
		return new Skip();
	}

	@Override
	public Statement createSequence(List<Statement> statements,
			SourceLocation sourceLocation) {
		return new Sequence(statements);
	}

	@Override
	public Type getDoubleType() {
		return new Type(Type.type.DOUBLE);
	}

	@Override
	public Type getBoolType() {
		return new Type(Type.type.BOOLEAN);
	}

	@Override
	public Type getGameObjectType() {
		return new Type(Type.type.CREATURE);
	}

	@Override
	public Type getDirectionType() {
		return new Type(Type.type.DIRECTION);
	}

	@Override
	public Program createProgram(Statement mainStatement,
			Map<String, Type> globalVariables) {
		return new Program(mainStatement, globalVariables);
	}



}
