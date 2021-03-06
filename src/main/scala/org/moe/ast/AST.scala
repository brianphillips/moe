package org.moe.ast

import org.moe.runtime._

abstract class AST

// AST containers

case class CompilationUnitNode(body: ScopeNode) extends AST
case class ScopeNode(body: StatementsNode) extends AST

case class StatementsNode(nodes: List[AST]) extends AST

case class IfStruct (
  var condition: AST,
  var body: AST, 
  var else_node: Option[IfStruct] = None
) extends AST

case class IfNode(if_node: IfStruct) extends AST 

// literals

/**
 * A literal Int node
 *
 * @param value The Int value of this node
 */
case class IntLiteralNode(value: Int) extends AST
case class FloatLiteralNode(value: Double) extends AST
case class StringLiteralNode(value: String) extends AST
case class BooleanLiteralNode(value: Boolean) extends AST

case class UndefLiteralNode() extends AST

case class SelfLiteralNode() extends AST
case class ClassLiteralNode() extends AST
case class SuperLiteralNode() extends AST

case class PairLiteralNode(key: AST, value: AST) extends AST

case class ArrayLiteralNode(values: List[AST]) extends AST
case class HashLiteralNode(map: List[AST]) extends AST

case class RangeLiteralNode(start: AST, end: AST) extends AST

// unary operators

case class PrefixUnaryOpNode(lhs: AST, operator: String)  extends AST
case class PostfixUnaryOpNode(rhs: AST, operator: String) extends AST

// binary operators

case class BinaryOpNode(lhs: AST, operator: String, rhs: AST) extends AST

// value lookup, assignment and declaration

case class ParameterNode(name: String) extends AST
case class SignatureNode(params: List[ParameterNode]) extends AST

case class ClassDeclarationNode(name: String, superclass: Option[String], body: StatementsNode) extends AST
case class PackageDeclarationNode(name: String, body: StatementsNode) extends AST
case class ConstructorDeclarationNode(signature: SignatureNode, body: StatementsNode) extends AST
case class DestructorDeclarationNode(signature: SignatureNode, body: StatementsNode) extends AST
case class MethodDeclarationNode(name: String, signature: SignatureNode, body: StatementsNode) extends AST
case class SubroutineDeclarationNode(name: String, signature: SignatureNode, body: StatementsNode) extends AST
case class AttributeDeclarationNode(name: String, expression: AST) extends AST
case class VariableDeclarationNode(name: String, expression: AST) extends AST

case class ClassAccessNode(name: String) extends AST
case class AttributeAccessNode(name: String) extends AST
case class VariableAccessNode(name: String) extends AST
case class HashElementAccessNode(hashName: String, key: AST) extends AST
case class ArrayElementAccessNode(arrayName: String, index: AST) extends AST

// TODO - these should get converted to binary ops
case class AttributeAssignmentNode(name: String, expression: AST) extends AST
case class VariableAssignmentNode(name: String, expression: AST) extends AST

// operations

case class MethodCallNode(invocant: AST, method_name: String, args: List[AST]) extends AST
case class SubroutineCallNode(function_name: String, args: List[AST]) extends AST

// statements

case class UnlessNode(unless_condition: AST, unless_body: AST) extends AST
case class UnlessElseNode(unless_condition: AST, unless_body: AST, else_body: AST) extends AST

case class TryNode(
  body: AST,
  catch_nodes: List[CatchNode],
  finally_nodes: List[FinallyNode])
  extends AST
case class CatchNode(type_name: String, local_name: String, body: AST) extends AST
case class FinallyNode(body: AST) extends AST

case class WhileNode(condition: AST, body: StatementsNode) extends AST
case class DoWhileNode(condition: AST, body: StatementsNode) extends AST

case class ForeachNode(topic: AST, list: AST, body: StatementsNode) extends AST
case class ForNode(init: AST, condition: AST, update: AST, body: StatementsNode) extends AST
