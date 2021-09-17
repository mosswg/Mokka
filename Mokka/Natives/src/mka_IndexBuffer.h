class IndexBuffer {
private:
	unsigned int m_RendererID;
	unsigned int m_Count;
public:
	IndexBuffer(JNIEnv*, const jintArray data);
	~IndexBuffer();
	
	void Bind() const;
	void UnBind() const;

	inline unsigned int GetCount() const { return m_Count; }
};